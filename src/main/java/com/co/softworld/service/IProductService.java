package com.co.softworld.service;

import com.co.softworld.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(int id);
    Product save(Product product);
}
