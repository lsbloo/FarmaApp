package com.farma.poc.features.onboarding.data.repository

import com.farma.poc.features.onboarding.data.dao.OnboardingDAO
import com.farma.poc.features.onboarding.data.task.OnboardingTask

class OnboardingRepository(private val onboardingTask: OnboardingTask, private val onboardingDAO: OnboardingDAO) {
}