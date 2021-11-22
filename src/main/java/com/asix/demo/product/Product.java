package com.asix.demo.product;

public class Product {
    private final long productId;
    private final String productName;

    public Product(Long productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    /*@Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                '}';
    }*/
}
