package com.chenqinhao.cat.castle.web.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenqinhao on 2017/7/2.
 */
@RestController
@RequestMapping(path = "hello")
public class HelloController {

    @Value("${spring.profiles.active}")
    private String profile;

    @RequestMapping("say")
    public String sayHello() {
        return profile;
    }

}
