package com.ksundaysky.repository;

import com.ksundaysky.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findById(int id);

}
