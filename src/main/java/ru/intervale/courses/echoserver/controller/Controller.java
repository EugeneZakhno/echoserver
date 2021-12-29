package ru.intervale.courses.echoserver.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/hello")
    public String hello () {
        return "hello";
    }



}
