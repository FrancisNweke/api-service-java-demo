package com.asix.demo.product;

import com.asix.demo.exception.CustomBadRequestException;
import com.asix.demo.exception.CustomInternalServerException;
import com.asix.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("displayProduct")
    public Product displayProduct(@RequestParam(value = "name", defaultValue = "Macbook Air 15inch") String name){
        return productService.displayProduct(counter.incrementAndGet(), name);
    }

    @GetMapping("getProductById/{id}")
    public Product getProductById(@PathVariable(value = "id") long id){
        throw new ResourceNotFoundException(String.format("Product with id: %s not found.", id));
    }

    @GetMapping("getSingleProduct")
    public Product getSingleProduct(@RequestParam(value = "name", defaultValue = "1") String name){
        throw new ResourceNotFoundException(String.format("Product with id: %s not found.", name));
    }

    @GetMapping("addProduct")
    public Product addProduct(){
        throw new CustomBadRequestException("Can't save product.");
    }

    @GetMapping("updateProduct")
    public Product updateProduct(){
        throw new CustomInternalServerException("Server can't be reached.");
    }
}
