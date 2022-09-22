package com.farma.poc.featuresApp.compose.login.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.base.BaseDTO
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tokenlogin")
data class ResponseLoginDTO(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    @ColumnInfo(name = "bearer_token")
    val bearerToken: String? = null,
    @ColumnInfo(name = "data_expires")
    val dataExpires: String? = null
): BaseDTO(),Parcelable
