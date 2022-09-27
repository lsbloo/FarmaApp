package com.farma.poc.featuresApp.compose.address.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO


@Dao
interface AddressDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddress(addressDTO: AddressDTO)
}