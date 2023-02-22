package com.co.softworld.service.serviceImpl;

import com.co.softworld.dao.IProductDao;
import com.co.softworld.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private IProductDao productDao;
    private final List<Product> productList = new ArrayList<>();
    private Product product;

    @BeforeEach
    void setUp() {
        openMocks(this);
        when(productDao.findAll())
                .thenReturn(asList(new Product(1, "computer", 400), new Product(2, "mouse", 10)));
        when(productDao.findById(1))
                .thenReturn(Optional.of(new Product(1, "computer", 400)));
        productList.add(new Product(1, "computer", 400));
        productList.add(new Product(2, "mouse", 10));
        product = new Product(1, "computer", 400);
    }

    @AfterEach
    void tearDown() {
        productService = null;
        productDao = null;
    }

    @Test
    void findAll_empty() {
        when(productDao.findAll()).thenReturn(new ArrayList<>());
        assertTrue(productService.findAll().isEmpty());
    }

    @Test
    void findAll() {
        assertEquals(productList, productService.findAll());
    }

    @Test
    void findById_null() {
        assertNull(productService.findById(3));
    }

    @Test
    void findById_idNotEquals() {
        product = new Product(2, "computer", 400);
        assertNotEquals(product, productService.findById(1));
    }

    @Test
    void findById_nameNotEquals() {
        product = new Product(1, "laptop", 400);
        assertNotEquals(product, productService.findById(1));
    }

    @Test
    void findById_priceNotEquals() {
        product = new Product(1, "computer", 401);
        assertNotEquals(product, productService.findById(1));
    }

    @Test
    void findById() {
        assertEquals(product, productService.findById(1));
    }
}