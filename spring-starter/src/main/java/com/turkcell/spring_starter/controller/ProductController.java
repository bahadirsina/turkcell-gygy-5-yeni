package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController //Anotasyon => Sınıfın bir RESTful web hizmeti denetleyicisi olduğunu belirtir.
@RequestMapping("/api/product") // localhost:8080/api/product
public class ProductController {

    @GetMapping("")
    public String sayHi(String name, int age) {
        return "Hi, " + name + "! You are " + age + " years old.";
    }
    @GetMapping("hello/{name}/{age}")
    public String sayHello(@PathVariable String name, @PathVariable int age) {
        return "Hello, " + name + "! You are " + age + " years old.";
    }
}
