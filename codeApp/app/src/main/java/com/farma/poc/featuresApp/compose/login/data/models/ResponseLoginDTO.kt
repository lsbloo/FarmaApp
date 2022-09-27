package com.farma.poc.featuresApp.compose.login.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.base.BaseDTO
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tokenlogin")
data class ResponseLoginDTO(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 1,
    @ColumnInfo(name = "bearer_token")
    @SerializedName("token")
    var bearerToken: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @ColumnInfo(name = "data_expires")
    var dataExpires: String? = null
): BaseDTO(),Parcelable
