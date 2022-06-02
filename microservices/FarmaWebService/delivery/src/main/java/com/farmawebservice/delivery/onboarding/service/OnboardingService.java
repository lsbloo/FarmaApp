package com.farmawebservice.delivery.onboarding.service;

import com.farmawebservice.delivery.onboarding.dto.ItemOnboardingDTO;
import com.farmawebservice.delivery.onboarding.dto.OnboardingDTO;
import com.farmawebservice.delivery.onboarding.repository.ItemOnboardingRepository;
import com.farmawebservice.delivery.onboarding.repository.OnboardingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OnboardingService {


    private final OnboardingRepository onboardingRepository;
    private final ItemOnboardingRepository itemOnboardingRepository;

    @Autowired
    public OnboardingService(OnboardingRepository onboardingRepository1, ItemOnboardingRepository itemOnboardingRepository1) {
        this.onboardingRepository = onboardingRepository1;
        this.itemOnboardingRepository = itemOnboardingRepository1;
    }



    public OnboardingDTO getOnboardingScreenSetup() {
        List<Integer> idsItemsOnboarding = this.onboardingRepository.getIdsFromItemOnboarding(1);
        List<ItemOnboardingDTO> itemOnboardingDTOList = new ArrayList<>();
        for(Integer id : idsItemsOnboarding) {
            itemOnboardingDTOList.add(this.itemOnboardingRepository.findById(id).get());
        }
        OnboardingDTO onboardingDto = new OnboardingDTO();
        onboardingDto.setOnboardingScreen(itemOnboardingDTOList);
        return onboardingDto;
    }
}
