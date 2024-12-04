package com.co.softworld.controller;

import com.co.softworld.entity.Product;
import com.co.softworld.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/microservices/product")
public class ProductController {

    private final IProductService productService;

    @GetMapping()
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Product findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @PostMapping()
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
}
