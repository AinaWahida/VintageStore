package com.myweb.util;

import com.myweb.model.User;
import java.io.*;
import java.util.ArrayList;

public class readwriteUser {
    private static final String FILE_NAME = "user.csv"; // Ensure this is the correct path to your CSV file

    // Read file into an ArrayList of User objects
    public static ArrayList<User> readFileUser() {
        ArrayList<User> userList = new ArrayList<>();
        File inputFile = new File(FILE_NAME);

        if (!inputFile.exists()) {
            System.err.println("File does not exist: " + FILE_NAME);
            return userList; // Return an empty user list if the file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            // Skip the header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // -1 to include empty values
                if (parts.length < 5) { // Adjusted to match User fields
                    System.err.println("Skipping invalid line: " + line);
                    continue; // Skip lines with insufficient data
                }

                // Parse parts into fields
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    String password = parts[3].trim();
                    String role = parts[4].trim();

                    // Create a User object and add it to the list
                    User user = new User(id, name, email, password, role);
                    userList.add(user);

                    System.out.println("Loaded user: " + user.getEmail());
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number in line: " + line);
                }
            }
            System.out.println("Successfully loaded file.");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return userList;
    }

    public static boolean writeUser(User user) {
        String fileName = "user.csv";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.append("\n")
                    .append(user.getId() + ",")
                    .append(user.getUsername() + ",")
                    .append(user.getEmail() + ",")
                    .append(user.getPassword() + ",")
                    .append("user");
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
}
