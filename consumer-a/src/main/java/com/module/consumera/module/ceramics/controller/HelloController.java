package com.module.consumera.module.ceramics.controller;

import com.module.consumera.module.ceramics.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    public Object getHelloService(String name) {
        Object obj = helloService.hello(name);
        return obj;
    }

    @RequestMapping("/hello2")
    public Object HelloService2(String name) {
        Object obj = helloService.hello2(name);
        return obj;
    }
}
