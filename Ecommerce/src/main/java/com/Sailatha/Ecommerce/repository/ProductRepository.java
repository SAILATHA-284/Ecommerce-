package com.Sailatha.Ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sailatha.Ecommerce.model.Product;

@Repository
public interface  ProductRepository  extends JpaRepository<Product, Long> {
    
}
