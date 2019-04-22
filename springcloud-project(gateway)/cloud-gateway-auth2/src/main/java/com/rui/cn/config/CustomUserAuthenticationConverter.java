package com.rui.cn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 自定义token转换器
 *
 * @author zhangrl
 * @time 2019/4/20-11:50
 **/
@Component
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    //    @Override
//    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
//        LinkedHashMap response = new LinkedHashMap();
//        String name = authentication.getName();
//        response.put("user_name", name);
//
//        Object principal = authentication.getPrincipal();
//        UserJwt userJwt = null;
//        if(principal instanceof  UserJwt){
//            userJwt = (UserJwt) principal;
//        }else{
//            //refresh_token默认不去调用userdetailService获取用户信息，这里我们手动去调用，得到 UserJwt
//            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
//            userJwt = (UserJwt) userDetails;
//        }
//        response.put("name", userJwt.getName());
//        response.put("id", userJwt.getId());
//        response.put("utype",userJwt.getUtype());
//        response.put("userpic",userJwt.getUserpic());
//        response.put("companyId",userJwt.getCompanyId());
//        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
//            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
//        }
//
//        return response;
//    }
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        LinkedHashMap response = new LinkedHashMap();
        String name = authentication.getName();
        response.put("user_name", name);
        Object principal = authentication.getPrincipal();
        User user = null;
        if (principal instanceof User) {
            user = (User) principal;
        } else {
            //refresh_token默认不去调用userdetailService获取用户信息，这里我们手动去调用，得到 UserJwt
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            user = (User) userDetails;
        }
        response.put("name", user.getUsername());
        response.put("password", user.getPassword());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }

        return response;
    }


}
