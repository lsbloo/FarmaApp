package com.farmawebservice.delivery.shop.repository;

import com.farmawebservice.delivery.base.repository.FarmaWebRepository;
import com.farmawebservice.delivery.shop.model.ProductShop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductShopRepository extends CrudRepository<ProductShop, Long>, FarmaWebRepository {


    Iterable<ProductShop> findAllByProductIdIn(Iterable<Long> productsId);

}
