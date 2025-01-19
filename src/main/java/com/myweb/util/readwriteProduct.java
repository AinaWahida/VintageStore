package com.myweb.util;

import com.myweb.model.Product;
import com.myweb.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class readwriteProduct {
    private static String fileName = "product.csv"; // Ensure this is the correct path to your CSV file

    // Read file into array
    public static ArrayList<Product> readFile() {

        ArrayList<Product> productList = new ArrayList<>();
        File inputFile = new File(fileName);

        if (!inputFile.exists()) {
            System.err.println("File does not exist: " + fileName);
            return productList; // Return an empty product list if the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            // Skip the header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // -1 to include empty values
                if (parts.length < 8) {
                    System.err.println("Skipping invalid line: " + line);
                    continue; // Skip lines with insufficient data
                }

                // Parse parts into fields
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    String description = parts[3].trim();
                    String imageUrl = parts[4].trim();
                    String category = parts[5].trim();
                    String sale = parts[6].trim();
                    String newArrival = parts[7].trim();

                    // Create a Product object and add to the list
                    Product product = new Product(id, name, price, description, imageUrl, category, sale, newArrival);
                    productList.add(product);

                    System.out.println("Loaded product: " + product.getName());
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number in line: " + line);
                }
            }
            System.out.println("Successfully loaded file.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return productList;
    }

    public static boolean writeProduct(List<Product> products) {
        String fileName = "product.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {  // Overwrite the file
            // Write header (optional)
            writer.write("ID,Name,Price,Description,ImageURL,Category,Sale,NewArrival");
            writer.newLine();  // Add a newline after the header

            // Write each product on a new line
            for (Product product : products) {
                String line = product.getId() + ","
                        + product.getName() + ","
                        + product.getPrice() + ","
                        + product.getDescription() + ","
                        + product.getImage() + ","
                        + product.getCategory() + ","
                        + product.getSale() + ","
                        + product.getNewArrival();
                writer.write(line);
                writer.newLine();  // Move to the next line after each product
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    public static Product getProductById(int productId) {
        ArrayList<Product> products = readFile();
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null; // Product not found
    }
}
