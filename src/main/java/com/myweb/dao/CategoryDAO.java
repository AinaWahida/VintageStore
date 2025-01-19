package com.myweb.dao;

import com.myweb.model.Category;
import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories();
    Category getCategoryById(int id);
}
