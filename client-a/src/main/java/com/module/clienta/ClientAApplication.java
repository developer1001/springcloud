package com.module.clienta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ClientAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi1")
    public String hello1(@RequestParam String name){
        return "hi " + name + ",i am from port:" + port;
    }

    @RequestMapping("/hi2")
    public Object hello2(@RequestParam String name){
        Map<String, Object> map = new HashMap<>();
        map.put("method", "hello2");
        map.put("dealResult","hi " + name + ",i am from port:" + port);
        return map;
    }
}
