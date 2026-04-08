package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.turkcell.spring_starter.model.Product;

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

    @PostMapping
    public Product add(@RequestBody Product product){ // Json->Java objesine
        // isim 1 haneden uzun mu?
        // fiyat..
        // DB'e kaydet..

        return product;
    }
}
