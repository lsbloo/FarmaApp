package com.farmawebservice.delivery.address.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private String number;

    private String state;

    private String cep;

    private Double latitude;

    private Double longitude;

    private String city;

    @Column(name="is_principal")
    private boolean isPrincipal;

}
