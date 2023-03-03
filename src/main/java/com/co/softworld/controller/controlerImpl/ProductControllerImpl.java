package com.co.softworld.controller.controlerImpl;

import com.co.softworld.controller.IProductController;
import com.co.softworld.entity.Product;
import com.co.softworld.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microservices/product")
public class ProductControllerImpl implements IProductController {

    @Autowired
    private IProductService productService;

    @Override
    @GetMapping("/findAll")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @Override
    @GetMapping("/findById/{id}")
    public Product findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @Override
    @PostMapping("")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
}
