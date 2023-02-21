package com.co.softworld.dao;

import com.co.softworld.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductDao extends JpaRepository<Product, Integer> {
}
