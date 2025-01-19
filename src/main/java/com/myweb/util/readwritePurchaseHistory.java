package com.myweb.util;

import com.myweb.model.Cart;
import com.myweb.model.Product;
import com.myweb.model.PurchaseHistory;
import com.myweb.model.User;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class readwritePurchaseHistory {
    private static String fileName = "history.csv"; // File name for purchase history

    public static ArrayList<PurchaseHistory> readFileHistory() {
        ArrayList<PurchaseHistory> historyList = new ArrayList<>();
        File inputFile = new File(fileName);

        if (!inputFile.exists()) {
            System.err.println("File does not exist: " + fileName);
            return historyList; // Return an empty user list if the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            // Skip the header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // -1 to include empty values
                if (parts.length < 7) { // Adjusted to match User fields
                    System.err.println("Skipping invalid line: " + line);
                    continue; // Skip lines with insufficient data
                }

                // Parse parts into fields
                try {
                    int userId = Integer.parseInt(parts[0].trim());
                    int productId = Integer.parseInt(parts[1].trim());
                    String product = parts[2].trim();
                    String address = parts[3].trim();
                    String paymentType = parts[4].trim();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date = dateFormat.parse(parts[5].trim());
                    double price = Double.parseDouble(parts[6].trim());

                    // Create a User object and add it to the list
                    PurchaseHistory history = new PurchaseHistory(userId, productId, product, address, paymentType, price, date);
                    historyList.add(history);

                    System.out.println("Loaded product: " + history.getProduct());
                } catch (NumberFormatException | ParseException e) {
                    System.err.println("Skipping invalid number in line: " + line);
                }
            }
            System.out.println("Successfully loaded file.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return historyList;
    }

    public static boolean savePurchaseToCSV(int userId, Cart cart, String address, String paymentType) {

        try (FileWriter writer = new FileWriter(fileName, true)) { // Open in append mode
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");
            String currentDate = dateFormat.format(new Date());  // Format the current date


            for (Product product : cart.getProducts()) {
                writer.append("\n") // Start a new line
                        .append(userId + ",")                   // User ID
                        .append(product.getId() + ",")          // Product ID
                        .append(product.getName() + ",")        // Product Name
                        .append(address.replace(",", " ") + ",") // Shipping Address (sanitize commas)
                        .append(paymentType + ",")            // Payment Type
                        .append(currentDate + ",")
                        .append(String.valueOf(product.getPrice())); // Product Price
            }
            return true; // Return true if writing is successful
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false; // Return false if an exception occurs
        }
    }
}
