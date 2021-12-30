package ru.intervale.courses.echoserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String hello () {
        return "hello, Spring";
    }

    @GetMapping("/withParams/{param}")
    public String withParams (@RequestParam String param) {
        return "withParams, spring " + param;
    }

    @GetMapping("/withPathVariable/{param}")
    public String withPathVariable (@PathVariable String id) {
        return "withParams, spring " + id;
    }

}
