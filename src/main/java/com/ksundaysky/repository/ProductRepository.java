package com.ksundaysky.repository;

import com.ksundaysky.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findById(int id);
    @Transactional
    Long deleteById(int id);

    Product findBySerial(String serial_number);
}
