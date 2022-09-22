package com.farma.poc.featuresApp.compose.home.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farma.poc.featuresApp.compose.home.data.models.ItemsHomeDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDAO {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItemsHome(itemsHomeDTO: ItemsHomeDTO)


    @Query(value = "select * from itemshomedto where id=:homeId")
    fun getItemsHomeCached(homeId: Long) : Flow<ItemsHomeDTO?>
}