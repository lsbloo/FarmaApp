package com.farmawebservice.delivery.onboarding.repository;

import com.farmawebservice.delivery.onboarding.dto.OnboardingDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnboardingRepository extends CrudRepository<OnboardingDTO, Integer> {



    @Query(value = "select onboarding_screen_id from onboardingdto_onboarding_screen where onboardingdto_id=?1", nativeQuery = true)
    List<Integer> getIdsFromItemOnboarding(Integer onboardingId);
}
