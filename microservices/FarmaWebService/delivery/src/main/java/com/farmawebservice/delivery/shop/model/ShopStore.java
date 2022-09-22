package com.farmawebservice.delivery.shop.model;


import com.farmawebservice.delivery.categories.model.Category;
import com.farmawebservice.delivery.deliveryzone.model.DeliveryZone;
import com.farmawebservice.delivery.products.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "shop_store")
@Getter
@Setter
public class ShopStore {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private String name;

    private String cnpj;

    @Column(name = "client_id_token")
    private String clientIdToken;


    @OneToMany(fetch = FetchType.LAZY)
    public List<ProductShop> productsShop;

    @OneToMany(fetch = FetchType.LAZY)
    public List<DeliveryZone> deliveryZones;

    @OneToMany(fetch = FetchType.LAZY)
    public List<ShopProductHighLight> shopProductHighLights;

    @OneToMany(fetch = FetchType.LAZY)
    public List<Category> categoryList;
}
