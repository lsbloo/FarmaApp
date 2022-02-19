package com.farma.poc.core.config.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farma.poc.core.config.constants.ConfigApplicationConstants.VERSION_DATABASE

@Database(entities = [Person::class], version = VERSION_DATABASE)
abstract class FarmaAppDatabase: RoomDatabase() {

}