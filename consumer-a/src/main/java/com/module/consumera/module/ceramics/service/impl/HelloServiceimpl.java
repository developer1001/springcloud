package com.module.consumera.module.ceramics.service.impl;

import com.module.consumera.module.ceramics.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloServiceimpl implements HelloService {
    @Autowired
    RestTemplate restTemplate;
    @Override
    public Object hello(@RequestParam String name) {
        return restTemplate.getForObject("http://SERVICE-A/hi1?name=" + name,String.class);
    }

    @Override
    // 断路器配置，当无法调用如下方法时，就会调用自定的hiError方法。
    @HystrixCommand(fallbackMethod = "hiError")
    public Object hello2(@RequestParam String name) {
        ResponseEntity<Object> responseEntity =
                restTemplate.getForEntity("http://service-a/hi2?name=" + name,Object.class);
        Object forObject =  restTemplate.getForObject("http://service-a/hi2?name=" + name,Object.class);
        Map<String, Object> map = new HashMap<>();
        map.put("responseEntity", responseEntity);
        map.put("forObject",forObject);
        return map;
    }
    public String hiError(String name)
    {
        return "hey " +
                name + ", there is some problem with hi page";
    }
}
