package com.farmawebservice.delivery.base.repository;

public interface FarmaWebRepository {

    void saveShopProduct(Long shop_store_id, Long product_shop_id);

    void deleteShopProduct(Long shop_store_id, Long product_shop_id);

    void saveShopDeliveryZone(Long shop_store_id, Long delivery_zone_id);

    void deleteShopDeliveryZone(Long shop_store_id, Long delivery_zone_id);

    void saveShopProductHighLight(Long shop_store_id, Long product_highlight_shop_id);

    void deleteShopProductHighLight(Long shop_store_id, Long product_highlight_shop_id);

    void saveShopCategoryProduct(Long shop_store_id, Long category_list_id);

    void deleteShopCategoryProduct(Long shop_store_id, Long category_list_id);
}
