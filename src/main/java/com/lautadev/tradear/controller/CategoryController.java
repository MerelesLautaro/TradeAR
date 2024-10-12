package com.lautadev.tradear.controller;

import com.lautadev.tradear.model.Category;
import com.lautadev.tradear.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
        return ResponseEntity.ok("Category saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> findCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findCategory(id);
        return category.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable Long id, @RequestBody Category category){
        return ResponseEntity.ok(categoryService.editCategory(id,category));
    }
}
