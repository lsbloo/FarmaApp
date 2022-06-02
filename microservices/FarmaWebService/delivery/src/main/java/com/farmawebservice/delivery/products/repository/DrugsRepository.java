package com.farmawebservice.delivery.products.repository;

import com.farmawebservice.delivery.products.model.Drug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsRepository extends CrudRepository<Drug,Long> {
}
