package com.myweb.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private List<PurchaseHistory> purchaseHistory;

    public User(int id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id;}
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<PurchaseHistory> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void addPurchaseHistory(PurchaseHistory purchase) {
        if (purchaseHistory == null) {
            purchaseHistory = new ArrayList<>();
        }
        purchaseHistory.add(purchase);
    }
}
