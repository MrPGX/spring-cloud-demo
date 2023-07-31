package com.pgx.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products/*")
public class procuctController {

    @GetMapping(value = "/{id}")
    public String getProduct(@PathVariable String id) {
        return "productId = " + id;
    }
}
