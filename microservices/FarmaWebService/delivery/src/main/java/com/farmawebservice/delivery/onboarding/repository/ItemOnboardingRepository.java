package com.farmawebservice.delivery.onboarding.repository;

import com.farmawebservice.delivery.onboarding.dto.ItemOnboardingDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOnboardingRepository extends CrudRepository<ItemOnboardingDTO, Integer> {

}
