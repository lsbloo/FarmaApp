package com.farma.poc.core.config.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farma.poc.core.base.Person
import com.farma.poc.core.config.constants.ConfigApplicationConstants.VERSION_DATABASE
import com.farma.poc.core.utils.converters.GeneralTypeConverter
import com.farma.poc.core.utils.dto.DrugDTO
import com.farma.poc.core.utils.dto.ProductDTO
import com.farma.poc.featuresApp.compose.address.data.dao.AddressDAO
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import com.farma.poc.featuresApp.compose.onboarding.data.dao.OnboardingDAO
import com.farma.poc.featuresApp.compose.onboarding.data.models.OnboardingDTO
import com.farma.poc.featuresApp.compose.home.data.dao.HomeDAO
import com.farma.poc.featuresApp.compose.home.data.models.CategoryDTO
import com.farma.poc.featuresApp.compose.home.data.models.HightLightsProductDTO
import com.farma.poc.featuresApp.compose.home.data.models.ItemsHomeDTO
import com.farma.poc.featuresApp.compose.home.data.models.SubCategoryDTO
import com.farma.poc.featuresApp.compose.login.data.dao.LoginDAO
import com.farma.poc.featuresApp.compose.login.data.models.ResponseLoginDTO
import com.farma.poc.featuresApp.compose.settings.home.data.dao.SettingsDAO
import com.farma.poc.featuresApp.compose.settings.home.data.model.GetSettingsDTO

@Database(
    entities = [Person::class, ResponseLoginDTO::class, ProductDTO::class,
        CategoryDTO::class, SubCategoryDTO::class, HightLightsProductDTO::class, OnboardingDTO::class, GetSettingsDTO::class,
               ItemsHomeDTO::class, DrugDTO::class, AddressDTO::class],
    version = VERSION_DATABASE
)
@TypeConverters(*[GeneralTypeConverter::class])
abstract class FarmaAppDatabase : RoomDatabase() {
    abstract fun onboardingDAO(): OnboardingDAO
    abstract fun addressDao(): AddressDAO
    abstract fun loginDao(): LoginDAO
    abstract fun homeDao(): HomeDAO
    abstract fun settingsDao(): SettingsDAO

}