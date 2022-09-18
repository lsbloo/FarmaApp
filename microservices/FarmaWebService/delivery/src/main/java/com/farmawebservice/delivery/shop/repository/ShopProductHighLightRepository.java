package com.farmawebservice.delivery.shop.repository;

import com.farmawebservice.delivery.base.repository.FarmaWebRepository;
import com.farmawebservice.delivery.shop.model.ShopProductHighLight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopProductHighLightRepository extends CrudRepository<ShopProductHighLight, Long>, FarmaWebRepository {


    Iterable<ShopProductHighLight> findAllByProductIdIn(Iterable<Long> ids);
}
