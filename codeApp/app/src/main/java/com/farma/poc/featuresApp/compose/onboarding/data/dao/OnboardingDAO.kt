package com.farma.poc.featuresApp.compose.onboarding.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farma.poc.featuresApp.compose.onboarding.data.models.OnboardingDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface OnboardingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnboardingDTO(onboardingDTO: OnboardingDTO)

    @Query("select * from onboarding where id=:idOnboarding")
    fun getOnboardingDataSet(idOnboarding: Int) : Flow<OnboardingDTO?>
}