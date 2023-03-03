package com.co.softworld.dao;

import com.co.softworld.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
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
        products.add(new Product(3, "desktop", 500));
        assertEquals(products, productDao.findAll());
        assertThat(productDao.findAll(), equalTo(products));
    }

    @Test
    void testFindByName_null() {
        assertThat(productDao.findByName("laptop").orElse(new Product()).getName(), nullValue());
    }

    @Test
    void testFindByName_assertThrow() {
        assertThrows(NoSuchElementException.class, productDao.findByName("laptop")::orElseThrow);
    }

    @Test
    void testFindByName() {
        assertThat(productDao.findByName("computer").orElse(new Product()).getName(), equalTo("computer"));
    }

    @Test
    void testFindByNameQuery() {
        assertThat(productDao.findByNameQuery("computer").orElse(new Product()).getName(), equalTo("computer"));
    }
}