package com.rui.cn.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @描述：同意拦截转换处理feign中的get方法传递参数问题
 * @创建人：[zhang]
 * @类名：FeignRequestInterceptor
 * @时间：2018/11/11 0011-上午 11:10
 **/
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            // feign 不支持 GET 方法传 POJO, json body转query
            if (requestTemplate.method().equals("GET") && requestTemplate.body() != null) {
                JsonNode jsonNode = objectMapper.readTree(requestTemplate.body());
                requestTemplate.body();
                Map<String, Collection<String>> query = new HashMap<>();
                buildQuery(jsonNode, "", query);
                requestTemplate.queries(query);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        if (!jsonNode.isContainerNode()) {   // 叶子节点
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> values = queries.get(path);
            if (null == values) {
                values = new ArrayList<>();
                queries.put(path, values);
            }
            values.add(jsonNode.asText());
            return;
        }
        if (jsonNode.isArray()) {   // 数组节点
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else {  // 根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }
    }
}
