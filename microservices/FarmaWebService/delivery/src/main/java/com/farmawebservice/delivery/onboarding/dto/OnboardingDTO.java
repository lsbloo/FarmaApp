package com.farmawebservice.delivery.onboarding.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "onboardingdto")
public class OnboardingDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="name")
    private String name;

    @OneToMany
    private List<ItemOnboardingDTO> onboardingScreen;
}
