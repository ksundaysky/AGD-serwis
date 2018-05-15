package com.ksundaysky.service;

import com.ksundaysky.model.Product;
import com.ksundaysky.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) { return productRepository.findById(id);}

    @Override
    public void updateProduct(Product product) { productRepository.save(product); }

    @Override
    public void deleteById(int id){

        productRepository.deleteById(id);
    }

    @Override
    public Product findBySerialNumber(String serial_number) {
        return productRepository.findBySerial(serial_number);
    }
}
