package com.co.softworld.dao;

import com.co.softworld.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductDaoTest {

    @Autowired
    private IProductDao productDao;

    @AfterEach
    void tearDown() {
        productDao = null;
    }

    @Test
    void testFindAll() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "computer", 400));
        products.add(new Product(2, "mouse", 10));
        assertEquals(products, productDao.findAll());
    }
}