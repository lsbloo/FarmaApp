package com.farmawebservice.delivery.shop.repository;

import com.farmawebservice.delivery.base.repository.FarmaWebRepository;
import com.farmawebservice.delivery.shop.model.ShopStore;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<ShopStore, Long> {

    List<ShopStore> findByClientIdToken(String clientIdToken);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO shop_store_category_list (shop_store_id,category_list_id) VALUES (?1,?2)", nativeQuery = true)
    void saveShopCategoryProduct(Long shop_store_id, Long category_list_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM shop_store_category_list where shop_store_id=?1 and category_list_id=?2", nativeQuery = true)
    void deleteShopCategoryProduct(Long shop_store_id, Long category_list_id);
    

}
