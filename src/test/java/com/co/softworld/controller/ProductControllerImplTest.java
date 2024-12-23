package com.co.softworld.controller;

import com.co.softworld.entity.Product;
import com.co.softworld.service.IProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IProductService productService;
    @MockBean
    private PasswordEncoder passwordEncoderMock;
    @MockBean
    private UserDetailsService userDetailsServiceMock;
    private RequestBuilder requestFindAll;
    private RequestBuilder requestFindById;

    @BeforeEach
    void setUp() {

        when(passwordEncoderMock.encode("password")).thenReturn("passwordTestEncoded");
        when(passwordEncoderMock.matches("password", "passwordTestEncoded")).thenReturn(true);

        UserDetails userDetails = User.builder()
                .username("test")
                .password(passwordEncoderMock.encode("password"))
                .authorities("ROLE_ADMIN")
                .build();

        when(userDetailsServiceMock.loadUserByUsername("test")).thenReturn(userDetails);

        requestFindAll = MockMvcRequestBuilders
                .get("/microservice/product")
                .with(httpBasic("test", "password"))
                .accept(APPLICATION_JSON);
        requestFindById = MockMvcRequestBuilders
                .get("/microservice/product/1")
                .with(httpBasic("test", "password"))
                .accept(APPLICATION_JSON);
        when(productService.findAll())
                .thenReturn(asList(new Product(1, "computer", 400), new Product(2, "mouse", 10)));
        when(productService.findById(1))
                .thenReturn(new Product(1, "computer", 400));
    }

    @AfterEach
    void tearDown() {
        mockMvc = null;
        productService = null;
        requestFindAll = null;
        requestFindById = null;
    }

    @Test
    void testFindAll_onlyOneElement() throws Exception {
        mockMvc.perform(requestFindAll)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1,name:\"computer\",price:400},{}]"))
                .andReturn();
    }

    @Test
    void testFindAll_onlyElementId() throws Exception {
        mockMvc.perform(requestFindAll)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1},{}]"))
                .andReturn();
    }

    @Test
    void testFindAll_onlyElementName() throws Exception {
        mockMvc.perform(requestFindAll)
                .andExpect(status().isOk())
                .andExpect(content().json("[{name:\"computer\"},{}]"))
                .andReturn();
    }

    @Test
    void testFindAll_onlyElementPrice() throws Exception {
        mockMvc.perform(requestFindAll)
                .andExpect(status().isOk())
                .andExpect(content().json("[{price:400},{}]"))
                .andReturn();
    }

    @Test
    void testFindAll_AllElementsStrict() throws Exception {
        mockMvc.perform(requestFindAll)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1,name:\"computer\",price:400},{id:2,name:\"mouse\",price:10}]", true))
                .andReturn();
    }

    @Test
    void testFindAll() throws Exception {
        mockMvc.perform(requestFindAll)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id:1},{id:2}]"))
                .andReturn();
    }

    @Test
    void testFindById_null() throws Exception {
        requestFindById = MockMvcRequestBuilders
                .get("/microservice/product/2")
                .with(httpBasic("test", "password"))
                .accept(APPLICATION_JSON);
        mockMvc.perform(requestFindById)
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    void testFindById_onlyElementId() throws Exception {
        mockMvc.perform(requestFindById)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1}"))
                .andReturn();
    }

    @Test
    void testFindById_onlyElementName() throws Exception {
        mockMvc.perform(requestFindById)
                .andExpect(status().isOk())
                .andExpect(content().json("{name:\"computer\"}"))
                .andReturn();
    }

    @Test
    void testFindById_onlyElementPrice() throws Exception {
        mockMvc.perform(requestFindById)
                .andExpect(status().isOk())
                .andExpect(content().json("{price:400}"))
                .andReturn();
    }

    @Test
    void testFindById_AllElementStrict() throws Exception {
        mockMvc.perform(requestFindById)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:1, name:\"computer\", price:400}"))
                .andReturn();
    }

    @Test
    void testFindById() throws Exception {
        mockMvc.perform(requestFindById)
                .andExpect(status().isOk())
                .andExpect(content().json("{}"))
                .andReturn();
    }

}