package com.myweb.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public void addProduct(Product product) {
        products.add(product);
    }

    public Cart() {
        this.products = new ArrayList<>();  // Ensure the list is initialized
    }

    public void removeProductById(int productId) {
        // Remove product by matching the integer ID
        products.removeIf(product -> product.getId() == productId);
    }


    public List<Product> getProducts() {
        return products;
    }

    public int getTotalItems() {
        return products.size();
    }
}
