package com.example.practice.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {

    @ResponseBody
    @RequestMapping(value = "/hello-world")
    public String helloWorld() {
        return "hello world";
    }
}
