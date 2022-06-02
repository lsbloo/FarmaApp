package com.farmawebservice.delivery.onboarding.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "item_onboarding")
public class ItemOnboardingDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String image;
    private String title;
    private String description;
    private String labelButton;
}
