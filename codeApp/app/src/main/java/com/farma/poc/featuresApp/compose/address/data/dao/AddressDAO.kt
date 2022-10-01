package com.farma.poc.featuresApp.compose.address.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farma.poc.featuresApp.compose.address.data.models.AddressDTO
import kotlinx.coroutines.flow.Flow


@Dao
interface AddressDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddress(addressDTO: AddressDTO)


    @Query(value = "select * from addresses")
    suspend fun getAllAddress(): List<AddressDTO>?
}