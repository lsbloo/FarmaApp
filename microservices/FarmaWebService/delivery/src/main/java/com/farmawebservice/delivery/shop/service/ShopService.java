package com.farmawebservice.delivery.shop.service;


import com.farmawebservice.delivery.address.repository.AddressRepository;
import com.farmawebservice.delivery.base.service.BaseService;
import com.farmawebservice.delivery.base.validator.core.Result;
import com.farmawebservice.delivery.base.validator.core.ValidatorBuilder;
import com.farmawebservice.delivery.base.validator.resource.ResourceMessage;
import com.farmawebservice.delivery.categories.model.Category;
import com.farmawebservice.delivery.categories.repository.CategoriesRepository;
import com.farmawebservice.delivery.products.repository.ProductsRepository;
import com.farmawebservice.delivery.shop.model.ProductShop;
import com.farmawebservice.delivery.shop.model.ShopProductHighLight;
import com.farmawebservice.delivery.shop.model.ShopStore;
import com.farmawebservice.delivery.shop.model.dto.*;
import com.farmawebservice.delivery.shop.repository.ProductShopRepository;
import com.farmawebservice.delivery.shop.repository.ShopProductHighLightRepository;
import com.farmawebservice.delivery.shop.repository.ShopRepository;
import com.farmawebservice.delivery.shop.validator.ShopValidator;
import com.farmawebservice.delivery.user.model.User;
import com.farmawebservice.delivery.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService extends BaseService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;
    private ProductShopRepository productShopRepository;
    private ShopProductHighLightRepository shopProductHighLightRepository;
    private CategoriesRepository categoriesRepository;
    private AddressRepository addressRepository;
    private final ShopValidator shopValidator;

    @Autowired
    public ShopService(ShopRepository shopRepositoryx, UserRepository userRepositoryx, ProductsRepository productsRepositoryx, ShopValidator shopValidatorx,
                       ProductShopRepository productShopRepositoryx, AddressRepository addressRepositoryx, ShopProductHighLightRepository shopProductHighLightRepositoryx,
                       CategoriesRepository categoriesRepositoryx) {
        this.shopRepository = shopRepositoryx;
        this.userRepository = userRepositoryx;
        this.productsRepository = productsRepositoryx;
        this.productShopRepository = productShopRepositoryx;
        this.addressRepository = addressRepositoryx;
        this.categoriesRepository = categoriesRepositoryx;
        this.shopValidator = shopValidatorx;
        this.shopProductHighLightRepository = shopProductHighLightRepositoryx;
    }


    public ResourceMessage deleteShopProduct(ShopStoreProductDTO dto) {
        setClientId(dto.getClient_id_token());
        User u = authenticateUserClient(userRepository);

        if (u != null) {
            Result result = new ValidatorBuilder<ShopStoreProductDTO>().apply(
                    this.shopValidator.validateShopStoreHaveShopProducts()
            ).validate(dto);

            if (result.ok()) {
                ShopStore shopStore = this.shopRepository.findByClientIdToken(dto.getClient_id_token());

                this.productShopRepository.deleteShopProduct(shopStore.getId(), Long.parseLong(dto.getProducts_shop_id()));

                this.productShopRepository.delete(
                        this.productShopRepository.findById(Long.parseLong(dto.getProducts_shop_id())).get()
                );

                return result.setResourceMessage("Shop Product has Deleted", false);
            } else return result.getResourceMessage();
        }
        return null;
    }

    public ResourceMessage deleteShopProductHighLight(ShopStoreProductDTO dto) {
        setClientId(dto.getClient_id_token());
        User u = authenticateUserClient(userRepository);

        if (u != null) {
            Result result = new ValidatorBuilder<ShopStoreProductDTO>().apply(
                    this.shopValidator.validateShopStoreHaveShopProducts()
            ).validate(dto);

            if (result.ok()) {
                ShopStore shopStore = this.shopRepository.findByClientIdToken(dto.getClient_id_token());

                this.shopProductHighLightRepository.deleteShopProductHighLight(shopStore.getId(), Long.parseLong(dto.getProducts_shop_highlight_id()));

                this.shopProductHighLightRepository.delete(
                        this.shopProductHighLightRepository.findById(Long.parseLong(dto.getProducts_shop_highlight_id())).get()
                );

                return result.setResourceMessage("Shop Product HighLight has Deleted", false);
            } else return result.getResourceMessage();
        }
        return null;
    }

    public ResourceMessage getShopProducts(ShopStoreProductDTO dto) {
        setClientId(dto.getClient_id_token());
        User u = authenticateUserClient(userRepository);

        if (u != null) {

            Result result = new ValidatorBuilder<ShopStoreProductDTO>().apply(
                    this.shopValidator.validateShopStoreHaveShopProducts()
            ).validate(dto);

            if (result.ok()) {
                ShopStore shopStore = this.shopRepository.findByClientIdToken(dto.getClient_id_token());
                List<ProductShop> productShopList = shopStore.getProductsShop();
                ShopProductResponseDTO responseDTO = new ShopProductResponseDTO();
                List<ProductDTO> productDTOList = new ArrayList<>();
                for (ProductShop productShop : productShopList) {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProduct(productShop.getProduct());
                    productDTO.setAmount(productShop.getAmount());
                    productDTO.setMaxValue(productShop.getMaxValue());
                    productDTO.setMinValue(productShop.getMinValue());
                    productDTO.setValue(productShop.getValue());
                    productDTO.setImage(productShop.getImage());
                    productDTOList.add(productDTO);
                }
                responseDTO.setProductList(productDTOList);
                responseDTO.setCnpj(shopStore.getCnpj());
                responseDTO.setName(shopStore.getName());

                return result.setResourceMessage("Products Found", false,
                        responseDTO);
            } else return result.getResourceMessage();
        }
        return null;
    }

    public ResourceMessage createShopProductCategory(ShopCategoryDTO dto) {
        setClientId(dto.getClient_id_token());
        User u = authenticateUserClient(userRepository);

        if (u != null) {
            Result result = new ValidatorBuilder<ShopCategoryDTO>().apply(
                    this.shopValidator.validateIfHasCategoryByShop()
            ).validate(dto);

            if (result.ok()) {
                ShopStore shop = this.shopRepository.findByClientIdToken(dto.getClient_id_token());

                Category category = new Category();
                category.setName(dto.getCategory().getName());
                category.setType(dto.getCategory().getType());
                category.setUrlImage(dto.getCategory().getUrlImage());
                this.shopRepository.saveShopCategoryProduct(shop.getId(), Long.parseLong(this.categoriesRepository.save(category).getId().toString()));
                return result.setResourceMessage("Category Has Create", false,
                        category);
            } else {
                return result.getResourceMessage();
            }
        }
        return null;
    }

    public ResourceMessage deleteShopProductCategory(ShopCategoryDTO dto) {
        setClientId(dto.getClient_id_token());
        User u = authenticateUserClient(userRepository);

        if (u != null) {
            Result result = new ValidatorBuilder<ShopCategoryDTO>().apply(
                    this.shopValidator.validateIfHasACategoryByShop()
            ).validate(dto);

            if (result.ok()) {
                try {
                    ShopStore shop = this.shopRepository.findByClientIdToken(dto.getClient_id_token());
                    Category category = this.categoriesRepository.findById(Integer.parseInt(dto.getCategory_id().toString())).get();
                    this.shopRepository.deleteShopCategoryProduct(shop.getId(), Long.parseLong(category.getId().toString()));
                    this.categoriesRepository.delete(category);
                    return result.setResourceMessage("Category Has Deleted", false,
                            category);
                } catch (Exception e) {
                    result.error("Don't Has Category inserted with name");
                    result.setResourceMessage("Don't Has Category inserted with name", true);
                    return result.getResourceMessage();
                }
            } else {
                return result.getResourceMessage();
            }
        }
        return null;
    }

    public ResourceMessage createShopProducts(ShopStoreProductDTO shopStoreDTO) {
        setClientId(shopStoreDTO.getClient_id_token());
        User u = authenticateUserClient(userRepository);

        if (u != null) {
            Result result = new ValidatorBuilder<ShopStoreProductDTO>().apply(
                    this.shopValidator.validateIfExistsShopProduct()
            ).validate(shopStoreDTO);

            if (result.ok()) {
                try {
                    createShopProductsValidated(shopStoreDTO, u);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return result.getResourceMessage();
        }
        return null;
    }

    public ResourceMessage createShopProductsHighLight(ShopStoreProductDTO shopStoreDTO) {
        setClientId(shopStoreDTO.getClient_id_token());
        User u = authenticateUserClient(userRepository);

        if (u != null) {
            Result result = new ValidatorBuilder<ShopStoreProductDTO>().apply(
                    this.shopValidator.validateIfExistsShopProductHighLight()
            ).validate(shopStoreDTO);

            if (result.ok()) {
                try {
                    createShopProductsHighLightValidated(shopStoreDTO, u);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return result.getResourceMessage();
        }
        return null;
    }

    private void createShopProductsHighLightValidated(ShopStoreProductDTO shopStoreDTO, User u) throws Exception {
        List<ShopProductHighLight> productShopList = new ArrayList<>();
        for (ShopProductHighLightDTO shopProductDTO : shopStoreDTO.getProductHighLightDTOList()) {
            ShopProductHighLight productShop = new ShopProductHighLight();
            productShop.setAmount(shopProductDTO.getAmount());
            productShop.setMaxValue(shopProductDTO.getMaxValue());
            productShop.setMinValue(shopProductDTO.getMinValue());
            productShop.setValue(shopProductDTO.getValue());
            productShop.setIsHighLightOnly(shopProductDTO.getIsHighLightOnly());
            productShop.setImage(shopProductDTO.getImage());
            productShop.setProduct(
                    this.productsRepository.findById(shopProductDTO.getId_product()).get()
            );
            productShopList.add(this.shopProductHighLightRepository.save(productShop));
        }

        ShopStore shop = this.shopRepository.findByClientIdToken(shopStoreDTO.getClient_id_token());
        for (ShopProductHighLight p1 : productShopList) {
            this.productShopRepository.saveShopProductHighLight(shop.getId(), p1.getId());
        }
    }

    private void createShopProductsValidated(ShopStoreProductDTO shopStoreDTO, User u) throws Exception {
        List<ProductShop> productShopList = new ArrayList<>();
        for (ShopProductDTO shopProductDTO : shopStoreDTO.getProductList()) {
            ProductShop productShop = new ProductShop();
            productShop.setAmount(shopProductDTO.getAmount());
            productShop.setMaxValue(shopProductDTO.getMaxValue());
            productShop.setMinValue(shopProductDTO.getMinValue());
            productShop.setValue(shopProductDTO.getValue());
            productShop.setImage(shopProductDTO.getImage());
            productShop.setProduct(this.productsRepository.findById(shopProductDTO.getId_product()).get());
            productShopList.add(this.productShopRepository.save(productShop));
        }

        ShopStore shop = this.shopRepository.findByClientIdToken(shopStoreDTO.getClient_id_token());
        for (ProductShop p1 : productShopList) {
            this.productShopRepository.saveShopProduct(shop.getId(), p1.getId());
        }
    }

    public ResourceMessage createShop(ShopStoreDTO shopStoreDTO) {
        setClientId(shopStoreDTO.getClient_id_token());
        authenticateUserClient(userRepository);
        User u = authenticateUserClient(userRepository);

        if (u != null) {
            Result result = new ValidatorBuilder<ShopStoreDTO>().apply(
                    this.shopValidator.validateIfExists()
            ).validate(shopStoreDTO);

            if (result.ok()) {
                Boolean hasErrorPersistence = createShopByUser(shopStoreDTO);
                ResourceMessage resourceMessage = result.getResourceMessage();
                resourceMessage.setIsError(!hasErrorPersistence);
                return resourceMessage;
            }
            return result.getResourceMessage();
        }
        return null;
    }


    private Boolean createShopByUser(ShopStoreDTO shopStoreDTO) {
        ShopStore shopStore = new ShopStore();
        shopStore.setCnpj(shopStoreDTO.getCnpj());
        shopStore.setClientIdToken(shopStoreDTO.getClient_id_token());
        shopStore.setName(shopStoreDTO.getName());
        return this.shopRepository.save(shopStore).getId() != -1;
    }

    public List<ShopStore> getAllShops() {
        return (List<ShopStore>) this.shopRepository.findAll();
    }

    public ShopStore getShopById(Long id) {
        return this.shopRepository.findById(id).get();
    }
}
