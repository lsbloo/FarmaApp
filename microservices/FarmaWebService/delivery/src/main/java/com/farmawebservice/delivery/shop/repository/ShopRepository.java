package com.farmawebservice.delivery.shop.repository;

import com.farmawebservice.delivery.base.repository.FarmaWebRepository;
import com.farmawebservice.delivery.shop.model.ShopStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends CrudRepository<ShopStore, Long>, FarmaWebRepository {

    ShopStore findByClientIdToken(String clientIdToken);
}
