package com.farmawebservice.delivery.shop.controller;


import com.farmawebservice.delivery.address.model.dto.AddressDTO;
import com.farmawebservice.delivery.base.validator.resource.ResourceMessage;
import com.farmawebservice.delivery.shop.model.dto.ShopCategoryDTO;
import com.farmawebservice.delivery.shop.model.dto.ShopStoreDTO;
import com.farmawebservice.delivery.shop.model.dto.ShopStoreProductDTO;
import com.farmawebservice.delivery.shop.service.ShopService;
import com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/shop")
public class ShopController {


    @Autowired
    private ShopService shopService;

    @DeleteMapping("/products/highlight")
    @ApiOperation(value = "delete shopProductsHighLight by shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted shopProductsHighLight"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> deleteShopProductHighLights(@RequestBody ShopStoreProductDTO shopStoreDTO) {
        ResourceMessage resourceMessage = this.shopService.deleteShopProductHighLight(shopStoreDTO);
        if (resourceMessage != null) {
            if (resourceMessage.getIsError()) {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont deleted", resourceMessage.getMessageValidator(), HttpServletResponse.SC_BAD_REQUEST));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("ShopStore Deleted", resourceMessage.getMessageValidator(), HttpServletResponse.SC_ACCEPTED));
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont deleted", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }

    @PostMapping("/products/highlight")
    @ApiOperation(value = "create shopProductsHighLight by shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created shopProductsHighLight"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> createShopProductHighLights(@RequestBody ShopStoreProductDTO shopStoreDTO) {
        ResourceMessage resourceMessage = this.shopService.createShopProductsHighLight(shopStoreDTO);
        if (resourceMessage != null) {
            if (resourceMessage.getIsError()) {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_BAD_REQUEST));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("ShopStore Created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_ACCEPTED));
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont created", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }


    @DeleteMapping("/products")
    @ApiOperation(value = "delete shopProducts by shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete shopProduct"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> deleteProductShop(@RequestBody ShopStoreProductDTO dto) {
        ResourceMessage resourceMessage = this.shopService.deleteShopProduct(dto);
        if (resourceMessage != null) {
            if (resourceMessage.getIsError()) {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont deleted", resourceMessage.getMessageValidator(), HttpServletResponse.SC_BAD_REQUEST));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("ShopStore Has deleted",
                        resourceMessage.getMessageValidator(), HttpServletResponse.SC_ACCEPTED, resourceMessage.getResponseDTO()));
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont deleted", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }


    @GetMapping("/products")
    @ApiOperation(value = "recovery shopProducts by shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recovery shopProducts"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> getProductShop(@RequestBody ShopStoreProductDTO dto) {

        ResourceMessage resourceMessage = this.shopService.getShopProducts(dto);
        if (resourceMessage != null) {
            if (resourceMessage.getIsError()) {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_BAD_REQUEST));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("ShopStore Created",
                        resourceMessage.getMessageValidator(), HttpServletResponse.SC_ACCEPTED, resourceMessage.getResponseDTO()));
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont created", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }

    }


    @PostMapping
    @ApiOperation(value = "create shopStore by shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully create shopStore"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> createShop(@RequestBody ShopStoreDTO shopStoreDTO) {
        ResourceMessage resourceMessage = this.shopService.createShop(shopStoreDTO);
        if (resourceMessage != null) {
            if (resourceMessage.getIsError()) {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_BAD_REQUEST));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("ShopStore Created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_ACCEPTED));
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont created", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }


    @PostMapping("/products")
    @ApiOperation(value = "create shopStore Products by shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully create shopStore Products"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> createShopProducts(@RequestBody ShopStoreProductDTO shopStoreDTO) {
        ResourceMessage resourceMessage = this.shopService.createShopProducts(shopStoreDTO);
        if (resourceMessage != null) {
            if (resourceMessage.getIsError()) {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_BAD_REQUEST));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("ShopStore Created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_ACCEPTED));
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopStore dont created", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }

    @PostMapping("/products/categories")
    @ApiOperation(value = "create a Category Product by shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully create a Category Product"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> createShopProductsCategory(@RequestBody ShopCategoryDTO shopStoreDTO) {
        ResourceMessage resourceMessage = this.shopService.createShopProductCategory(shopStoreDTO);
        if (resourceMessage != null) {
            if (resourceMessage.getIsError()) {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopCategoryProduct don't created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_BAD_REQUEST));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("ShopCategoryProduct Created", resourceMessage.getMessageValidator(), HttpServletResponse.SC_ACCEPTED));
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopCategoryProduct don't created", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }


    @DeleteMapping("/products/categories")
    @ApiOperation(value = "create a Category Product by shop", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete a Category Product"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> deleteShopProductsCategory(@RequestBody ShopCategoryDTO shopStoreDTO) {
        ResourceMessage resourceMessage = this.shopService.deleteShopProductCategory(shopStoreDTO);
        if (resourceMessage != null) {
            if (resourceMessage.getIsError()) {
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopCategoryProduct don't deleted", resourceMessage.getMessageValidator(), HttpServletResponse.SC_BAD_REQUEST));
            } else {
                return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("ShopCategoryProduct deleted", resourceMessage.getMessageValidator(), HttpServletResponse.SC_ACCEPTED));
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("ShopCategoryProduct don't deleted", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }

}
