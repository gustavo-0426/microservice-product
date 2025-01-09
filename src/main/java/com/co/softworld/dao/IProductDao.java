package com.co.softworld.dao;

import com.co.softworld.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IProductDao extends CrudRepository<Product, Integer> {
    Optional<Product> findByName(String name);

    @Query("select p from Product p where p.name = ?1")
    Optional<Product> findByNameQuery(String name);
}
