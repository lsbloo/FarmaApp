package com.farmawebservice.delivery.categories.repository;

import com.farmawebservice.delivery.categories.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Integer> {
}
