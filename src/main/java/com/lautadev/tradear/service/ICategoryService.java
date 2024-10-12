package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    public Category saveCategory(Category category);
    public List<Category> getCategories();
    public Optional<Category> findCategory(Long id);
    public void deleteCategory(Long id);
    public Category editCategory(Long id, Category category);
}
