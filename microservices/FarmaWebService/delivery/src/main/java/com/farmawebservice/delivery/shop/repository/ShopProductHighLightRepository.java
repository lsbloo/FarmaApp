package com.farmawebservice.delivery.shop.repository;

import com.farmawebservice.delivery.base.repository.FarmaWebRepository;
import com.farmawebservice.delivery.shop.model.ShopProductHighLight;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ShopProductHighLightRepository extends CrudRepository<ShopProductHighLight, Long> {


    Iterable<ShopProductHighLight> findAllByProductIdIn(Iterable<Long> ids);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM shop_store_shop_product_high_lights where shop_store_id=?1 and shop_product_high_lights_id=?2", nativeQuery = true)
    void deleteShopProductHighLight(Long shop_store_id, Long product_highlight_shop_id);

}
