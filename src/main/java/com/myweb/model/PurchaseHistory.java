package com.myweb.model;

import java.util.Date;

public class PurchaseHistory {
    private int userId;
    private int productId;
    private String product;
    private String address;
    private String paymentType;
    private double price;
    private Date date;

    // Constructor to create a new purchase history entry
    public PurchaseHistory(int userId, int productId,String product, String address, String paymentType, double price, Date date) {
        this.userId = userId;
        this.productId = productId;
        this.product = product;
        this.address = address;
        this.paymentType = paymentType;
        this.price = price;
        this.date = date; // Store the current date as the purchase date
    }

    public int getUserId() { return userId; }
    public int getProductId() { return productId; }
    public String getProduct() { return product; }
    public String getAddress() { return address; }
    public String getPaymentType() { return paymentType; }
    public double getPrice() { return price; }
    public Date getDate() { return date; }

}
