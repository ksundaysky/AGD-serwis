package com.ksundaysky.service;

import com.ksundaysky.model.Product;

import java.util.List;

public interface ProductService {

    public void saveProduct(Product product);
    public List<Product> findAll();
    public Product findById(int id);
    public void updateProduct(Product product);
    public void deleteById(int id);
}
