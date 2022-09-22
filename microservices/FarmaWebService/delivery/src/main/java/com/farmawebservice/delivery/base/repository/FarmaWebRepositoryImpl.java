package com.farmawebservice.delivery.base.repository;

import javax.persistence.*;

public class FarmaWebRepositoryImpl implements FarmaWebRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void saveShopProduct(Long shop_store_id, Long product_shop_id) {
        String query = "INSERT INTO shop_store_products_shop (shop_store_id,products_shop_id) VALUES (?1,?2)";
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.createNativeQuery(query)
                .setParameter(1, shop_store_id)
                .setParameter(2, product_shop_id).executeUpdate();
        et.commit();
    }

    @Override
    public void deleteShopProduct(Long shop_store_id, Long product_shop_id) {
        String query = "DELETE FROM shop_store_products_shop where shop_store_id=?1 and products_shop_id=?2";
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.createNativeQuery(query).setParameter(1, shop_store_id).setParameter(2, product_shop_id).executeUpdate();
        et.commit();
    }

    @Override
    public void saveShopDeliveryZone(Long shop_store_id, Long delivery_zone_id) {
        String query = "INSERT INTO shop_store_delivery_zones (shop_store_id,delivery_zones_id) VALUES (?1,?2)";
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.createNativeQuery(query)
                .setParameter(1, shop_store_id)
                .setParameter(2, delivery_zone_id).executeUpdate();
        et.commit();
    }

    @Override
    public void deleteShopDeliveryZone(Long shop_store_id, Long delivery_zone_id) {
        String query = "DELETE FROM shop_store_delivery_zones where shop_store_id=?1 and delivery_zones_id=?2";
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.createNativeQuery(query).setParameter(1, shop_store_id).setParameter(2, delivery_zone_id).executeUpdate();
        et.commit();
    }

    @Override
    public void saveShopProductHighLight(Long shop_store_id, Long product_highlight_shop_id) {
        String query = "INSERT INTO shop_store_shop_product_high_lights (shop_store_id,shop_product_high_lights_id) VALUES (?1,?2)";
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.createNativeQuery(query)
                .setParameter(1, shop_store_id)
                .setParameter(2, product_highlight_shop_id).executeUpdate();
        et.commit();
    }

    @Override
    public void deleteShopProductHighLight(Long shop_store_id, Long product_highlight_shop_id) {
        String query = "DELETE FROM shop_store_shop_product_high_lights where shop_store_id=?1 and shop_product_high_lights_id=?2";
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.createNativeQuery(query).setParameter(1, shop_store_id).setParameter(2, product_highlight_shop_id).executeUpdate();
        et.commit();
    }

    @Override
    public void saveShopCategoryProduct(Long shop_store_id, Long category_list_id) {
        String query = "INSERT INTO shop_store_category_list (shop_store_id,category_list_id) VALUES (?1,?2)";
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.createNativeQuery(query)
                .setParameter(1, shop_store_id)
                .setParameter(2, category_list_id).executeUpdate();
        et.commit();
    }

    @Override
    public void deleteShopCategoryProduct(Long shop_store_id, Long category_list_id) {
        String query = "DELETE FROM shop_store_category_list where shop_store_id=?1 and category_list_id=?2";
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction et = manager.getTransaction();
        et.begin();
        manager.createNativeQuery(query).setParameter(1, shop_store_id).setParameter(2, category_list_id).executeUpdate();
        et.commit();
    }
}
