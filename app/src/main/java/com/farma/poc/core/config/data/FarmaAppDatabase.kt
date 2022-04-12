package com.farma.poc.core.config.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farma.poc.core.config.constants.ConfigApplicationConstants.VERSION_DATABASE
import com.farma.poc.core.utils.converters.GeneralTypeConverter
import com.farma.poc.core.utils.dto.ProductDTO
import com.farma.poc.features.onboarding.data.dao.OnboardingDAO
import com.farma.poc.features.onboarding.data.models.OnboardingDTO
import com.farma.poc.home.data.dao.HomeDAO
import com.farma.poc.home.data.models.CategoryDTO
import com.farma.poc.home.data.models.HightLightsProductDTO
import com.farma.poc.home.data.models.SubCategoryDTO
import com.farma.poc.login.data.dao.LoginDAO
import com.farma.poc.login.data.models.ResponseLoginDTO

@Database(
    entities = [Person::class, ResponseLoginDTO::class, ProductDTO::class,
        CategoryDTO::class, SubCategoryDTO::class,HightLightsProductDTO::class, OnboardingDTO::class],
    version = VERSION_DATABASE
)
@TypeConverters(*[GeneralTypeConverter::class])
abstract class FarmaAppDatabase : RoomDatabase() {
    abstract fun onboardingDAO(): OnboardingDAO
    abstract fun loginDao(): LoginDAO
    abstract fun homeDao(): HomeDAO
}