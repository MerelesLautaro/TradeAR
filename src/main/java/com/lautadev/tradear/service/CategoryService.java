package com.lautadev.tradear.service;

import com.lautadev.tradear.model.Category;
import com.lautadev.tradear.repository.ICategoryRepository;
import com.lautadev.tradear.throwable.EntityNotFoundException;
import com.lautadev.tradear.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        if(category != null) {
            return categoryRepository.save(category);
        }
        return null;
    }


    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findCategory(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category editCategory(Long id, Category category) {
        Category categoryEdit = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(category,categoryEdit);

        return this.saveCategory(categoryEdit);
    }
}
