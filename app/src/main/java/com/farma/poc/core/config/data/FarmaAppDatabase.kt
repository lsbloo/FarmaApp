package com.farma.poc.core.config.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farma.poc.core.config.constants.ConfigApplicationConstants.VERSION_DATABASE
import com.farma.poc.login.data.dao.LoginDAO
import com.farma.poc.login.data.models.ResponseLoginDTO

@Database(entities = [Person::class, ResponseLoginDTO::class], version = VERSION_DATABASE)
abstract class FarmaAppDatabase: RoomDatabase() {
    abstract fun loginDao(): LoginDAO
}