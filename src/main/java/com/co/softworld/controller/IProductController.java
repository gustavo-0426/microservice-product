package com.co.softworld.controller;

import com.co.softworld.entity.Product;

import java.util.List;

public interface IProductController {
    List<Product> findAll();
    Product findById(int id);
}
