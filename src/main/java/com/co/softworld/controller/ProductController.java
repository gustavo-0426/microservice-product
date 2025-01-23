package com.co.softworld.controller;

import com.co.softworld.entity.Product;
import com.co.softworld.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/${api.version}/microservice/product")
@Tag(name = "ProductController")
public class ProductController {

    private final IProductService productService;

    @Operation(summary = "findAll, consulta todos los productos")
    @GetMapping()
    public List<Product> findAll() {
        return productService.findAll();
    }

    @Operation(summary = "findById, consulta el producto por id")
    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @Operation(summary = "save, registra el producto")
    @PostMapping()
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
}
