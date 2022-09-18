package com.farmawebservice.delivery.address.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {

    private String street;

    private String number;

    private String state;

    private String cep;

    private Double latitude;

    private Double longitude;

    private String city;

    private String client_id_token;
}
