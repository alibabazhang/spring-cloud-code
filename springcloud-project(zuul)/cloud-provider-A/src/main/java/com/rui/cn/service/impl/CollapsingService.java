package com.rui.cn.service.impl;

import com.netflix.hystrix.HystrixCollapser.Scope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rui.cn.entity.Animal;
import com.rui.cn.service.ICollapsingService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
/**
* hystrix请求合并
* @HystrixCollapser注解代表请求合并，调用的方法实际上是 batchMethod 指定的方法，timerDelayInMilliseconds 参数是指合并多少ms内的请求，暂时未1000也就是1s内的所有请求，若是不配置默认则是 10ms
 * 注意feign调用的话不适合 collapser
 * scope = GLOBAL 参数：合并作用域，默认是REQUEST，就是不会跨越多个请求会话的，只在当前用户请求中合并多次请求为批处理请求。这里改成GLOBAL，就是可以跨越request context，合并不同用户的请求为一次批处理请求。
 *
* @author zhangrl
* @time 2018/11/19-14:23
**/
@Component
public class CollapsingService implements ICollapsingService {

    @Override
    @HystrixCollapser(batchMethod = "collapsingList", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public Future<Animal> collapsing(Integer id) {
        return null;
    }

    @Override
    @HystrixCollapser(batchMethod = "collapsingListGlobal", scope = Scope.GLOBAL, collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public Future<Animal> collapsingGlobal(Integer id) {
        return null;
    }

    @HystrixCollapser(batchMethod = "collapsingList", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    @Override
    public Animal collapsingSyn(Integer id) {
        return null;
    }

    @HystrixCommand
    public List<Animal> collapsingList(List<Integer> animalParam) {
        System.out.println("collapsingList当前线程" + Thread.currentThread().getName());
        System.out.println("当前请求参数个数:" + animalParam.size());
        List<Animal> animalList = new ArrayList<Animal>();
        for (Integer animalNumber : animalParam) {
            Animal animal = new Animal();
            animal.setName("Cat - " + animalNumber);
            animal.setSex("male");
            animal.setAge(animalNumber);
            animalList.add(animal);
        }
        return animalList;
    }


    @HystrixCommand
    public List<Animal> collapsingListGlobal(List<Integer> animalParam) {
        System.out.println("collapsingListGlobal当前线程" + Thread.currentThread().getName());
        System.out.println("当前请求参数个数:" + animalParam.size());
        List<Animal> animalList = new ArrayList<Animal>();
        for (Integer animalNumber : animalParam) {
            Animal animal = new Animal();
            animal.setName("Dog - " + animalNumber);
            animal.setSex("male");
            animal.setAge(animalNumber);
            animalList.add(animal);
        }
        return animalList;
    }

}