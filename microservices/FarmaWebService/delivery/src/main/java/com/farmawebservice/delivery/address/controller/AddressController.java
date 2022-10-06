package com.farmawebservice.delivery.address.controller;


import com.farmawebservice.delivery.address.model.Address;
import com.farmawebservice.delivery.address.model.dto.AddressDTO;
import com.farmawebservice.delivery.address.model.dto.DAddressDTO;
import com.farmawebservice.delivery.address.service.AddressService;
import com.farmawebservice.delivery.base.model.MessageClientResponseDTO;
import com.farmawebservice.delivery.base.validator.resource.ResourceMessage;
import com.farmawebservice.delivery.util.messages.dtos.MessageResponseDTO;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {


    @Autowired
    private AddressService addressService;

    @PostMapping
    @ApiOperation(value = "save address by user", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recovery"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> saveAddressByUser(@RequestBody AddressDTO addressDTO) {
        if (this.addressService.saveAddressByUser(addressDTO)) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("Address Created", "Successful", HttpServletResponse.SC_ACCEPTED));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("Address dont created", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }

    @DeleteMapping
    @ApiOperation(value = "delete address by user", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> deleteAddressByUser(@RequestParam("client_id_token") String token, @RequestParam("address_id") Long id) {
        if (this.addressService.deleteAddressByUser(token, id)) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("Address Deleted", "Successful", HttpServletResponse.SC_ACCEPTED));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("Address dont delete", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }

    @GetMapping
    @ApiOperation(value = "get only address by user", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recovery"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> recoveryAddressByUser(@RequestBody DAddressDTO dto) {
        Address address = this.addressService.getAddressUserById(dto);
        if (address != null) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("Address recovery", "Successful", HttpServletResponse.SC_ACCEPTED, address));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("Don't get address", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }

    @GetMapping("/all")
    @ApiOperation(value = "get all address by user", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recovery"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageResponseDTO> recoveryAllAddressByUser(@RequestParam("client_id_token") String token) {
        List<Address> addressList = this.addressService.getAllAddressUserById(token);
        if (addressList != null && addressList.size() != 0) {
            return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(MessageResponseDTO.build("Address recovery", "Successful", HttpServletResponse.SC_ACCEPTED,
                    new Gson().toJson(addressList)));
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(MessageResponseDTO.build("Don't get address", ResourceMessage.GENERIC_ERROR, HttpServletResponse.SC_BAD_REQUEST));
        }
    }

    @GetMapping("/select")
    @ApiOperation(value = "get all address by user", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully recovery"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Error Internal")
    })
    public ResponseEntity<MessageClientResponseDTO> selectAddressPrincipalByUser(@RequestParam("address_id") Long address_id,
                                                                                 @RequestParam("is_principal") Boolean value,
                                                                                 @RequestParam("client_id_token") String client_id_token) {
        MessageClientResponseDTO responseDTO = this.addressService.selectAddressToPrincipal(address_id, value, client_id_token);
        if (responseDTO != null) {
            return ResponseEntity.status(responseDTO.getHttpStatusCode()).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(null);
        }
    }
}
