package com.farma.poc.features.onboarding.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farma.poc.features.onboarding.data.models.OnboardingDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface OnboardingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnboardingDTO(onboardingDTO: OnboardingDTO)

    @Query("select * from onboarding where id=:idOnboarding")
    fun getOnboardingDataSet(idOnboarding: Int) : Flow<OnboardingDTO?>
}