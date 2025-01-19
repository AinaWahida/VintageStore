package com.myweb.dao;

import com.myweb.model.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Vintage Clocks"));
        categories.add(new Category(2, "Retro Chairs"));
        return categories;
    }

    @Override
    public Category getCategoryById(int id) {
        // Return a mock category for simplicity
        return new Category(id, "Vintage Clocks");
    }
}
