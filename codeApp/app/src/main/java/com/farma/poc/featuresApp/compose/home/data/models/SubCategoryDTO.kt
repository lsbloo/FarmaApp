package com.farma.poc.featuresApp.compose.home.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "subcategories")
@Parcelize
data class SubCategoryDTO(
    var name: String?= null,
    @PrimaryKey
    var id: Long?= null
): Parcelable
