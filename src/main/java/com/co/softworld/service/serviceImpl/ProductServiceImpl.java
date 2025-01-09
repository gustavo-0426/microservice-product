package com.co.softworld.service.serviceImpl;

import com.co.softworld.dao.IProductDao;
import com.co.softworld.entity.Product;
import com.co.softworld.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductDao productDao;

    @Override
    public List<Product> findAll() {
        Iterable<Product> products = productDao.findAll();
        return StreamSupport
                .stream(products.spliterator(), false)
                .collect(Collectors.toList());
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
