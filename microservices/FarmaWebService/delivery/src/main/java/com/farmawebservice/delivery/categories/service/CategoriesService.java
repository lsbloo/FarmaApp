package com.farmawebservice.delivery.categories.service;


import com.farmawebservice.delivery.categories.model.Category;
import com.farmawebservice.delivery.categories.model.dto.CategoryDTO;
import com.farmawebservice.delivery.categories.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {


    private final CategoriesRepository categoriesRepository;


    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository1) {
        this.categoriesRepository = categoriesRepository1;
    }


    public Boolean saveCategory(CategoryDTO category) {
        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setType(category.getType());
        category1.setUrlImage(category.getUrlImage());
        Category re = this.categoriesRepository.save(category1);
        return re.getId() != -1;
    }


    public Iterable<Category> getAllCategories() {
        return this.categoriesRepository.findAll();
    }

    public Boolean deleteCategoryById(Integer id) {
        this.categoriesRepository.delete(this.categoriesRepository.findById(id).get());
        return this.categoriesRepository.findById(id).isPresent();
    }
}
