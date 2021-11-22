package com.asix.demo.product;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    public Product displayProduct(Long id, String name){
      return new Product(id, name);
    };
}
