package com.farmawebservice.delivery.shop.repository;

import com.farmawebservice.delivery.base.repository.FarmaWebRepository;
import com.farmawebservice.delivery.shop.model.ProductShop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProductShopRepository extends CrudRepository<ProductShop, Long> {


    Iterable<ProductShop> findAllByProductIdIn(Iterable<Long> productsId);

    @Query(nativeQuery = true, value = "INSERT INTO shop_store_products_shop (shop_store_id,products_shop_id) VALUES (?1,?2)")
    void saveShopProduct(Long shop_store_id, Long products_shop_id);

    @Query(nativeQuery = true, value = "DELETE FROM shop_store_products_shop where shop_store_id=?1 and products_shop_id=?2")
    void deleteShopProduct(Long shop_store_id, Long product_shop_id);

    @Query(nativeQuery = true, value = "INSERT INTO shop_store_shop_product_high_lights (shop_store_id,shop_product_high_lights_id) VALUES (?1,?2)")
    void saveShopProductHighLight(Long shop_store_id, Long product_highlight_shop_id);
}
