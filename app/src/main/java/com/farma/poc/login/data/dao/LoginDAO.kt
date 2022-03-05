package com.farma.poc.login.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farma.poc.login.data.models.ResponseLoginDTO

@Dao
interface LoginDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginToken(loginDTO: ResponseLoginDTO)

    @Query("select * from tokenlogin where id=:idUser")
    fun getLoginToken(idUser: Int): LiveData<ResponseLoginDTO?>

}