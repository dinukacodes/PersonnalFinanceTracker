package com.example.FinanceTracker.Service;

import com.example.FinanceTracker.Models.Category;
import com.example.FinanceTracker.Models.User;
import com.example.FinanceTracker.Repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
    public Category getCategoryById(long id) {
        return categoryRepo.findById(id).orElse(null);
    }
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
    public Category updateCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> getCategoriesByUser(User user) {
        return categoryRepo.findByUser(user);
    }
}
