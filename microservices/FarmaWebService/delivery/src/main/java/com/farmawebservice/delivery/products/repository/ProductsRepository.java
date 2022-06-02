package com.farmawebservice.delivery.products.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.farmawebservice.delivery.products.model.Product;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Product,Long> {


    @Query(value = "SELECT * FROM products",nativeQuery = true)
    public List<Product> getAllProducts();
}
