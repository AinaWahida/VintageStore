package com.myweb.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private String category;
    private String sale;
    private int quantity;
    private String newArrival;

    public Product(int id, String name, double price, String description, String image, String category, String sale, String newArrival) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
        this.sale = sale;
        this.newArrival = newArrival;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
    public String getCategory() { return category; }
    public String getSale() { return sale; }
    public String getNewArrival() { return newArrival; }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setImg(String image) {
        this.image = image;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setOnSale(String sale) {
        this.sale = sale;
    }
    public void setNewArrival(String newArrival) {
        this.newArrival = newArrival;
    }
}
