package com.co.softworld.service.serviceImpl;

import com.co.softworld.dao.IProductDao;
import com.co.softworld.entity.Product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;

import java.util.*;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private IProductDao productDao;
    private final List<Product> productList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        openMocks(this);
        when(productDao.findAll())
                .thenReturn(asList(new Product(1, "computer", 400), new Product(2, "mouse", 10)));
        when(productDao.findById(1))
                .thenReturn(Optional.of(new Product(1, "computer", 400)));
        productList.add(new Product(1, "computer", 400));
        productList.add(new Product(2, "mouse", 10));
    }

    @AfterEach
    void tearDown() {
        productService = null;
        productDao = null;
    }

    static List<Product> parameters() {
        return Arrays.asList(
                new Product(2, "computer", 400),
                new Product(1, "laptop", 400),
                new Product(1, "computer", 401));
    }

    @Test
    void findAll_empty() {
        when(productDao.findAll()).thenReturn(new ArrayList<>());
        assertThat(productService.findAll(), empty());
    }

    @Test
    void findAll() {
        assertThat(productService.findAll(), equalTo(productList));
    }

    @Test
    void findById_null() {
        assertThat(productService.findById(3), nullValue());
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void findById_NotEqualsIdNamePrice(Product product) {
        assertThat(productService.findById(1), not(product));
    }

    @Test
    void findById() {
        assertThat(productService.findById(1), equalTo(new Product(1, "computer", 400)));
    }
}