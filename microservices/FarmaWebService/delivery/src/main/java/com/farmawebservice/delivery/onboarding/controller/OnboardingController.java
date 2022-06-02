package com.farmawebservice.delivery.onboarding.controller;

import com.farmawebservice.delivery.onboarding.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/onboarding")
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @GetMapping("/all")
    private ResponseEntity<?> getAllOnboardingData(){
        return ResponseEntity.ok().body(this.onboardingService.getOnboardingScreenSetup());
    }
}
