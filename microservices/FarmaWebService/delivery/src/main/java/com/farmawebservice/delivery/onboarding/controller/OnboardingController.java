package com.farmawebservice.delivery.onboarding.controller;

import com.farmawebservice.delivery.onboarding.service.OnboardingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "return all onboarding data screen", notes = "get all data screen to generate onboarding UX")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The onboarding screen payload dont found"),
            @ApiResponse(code = 500,message = "Error Internal")
    })
    private ResponseEntity<?> getAllOnboardingData(){
        return ResponseEntity.ok().body(this.onboardingService.getOnboardingScreenSetup());
    }
}
