package com.farma.poc.features.settings.home.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farma.poc.features.settings.home.data.model.GetSettingsDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSettings(data: GetSettingsDTO)

    @Query("select * from getsettingsdto where id=:idSettings")
    fun getSettingsFlow(idSettings: Int) : Flow<GetSettingsDTO?>

}