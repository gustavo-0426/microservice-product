package com.co.softworld.service.serviceImpl;

import com.co.softworld.dao.IProductDao;
import com.co.softworld.entity.Product;
import com.co.softworld.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }
}
