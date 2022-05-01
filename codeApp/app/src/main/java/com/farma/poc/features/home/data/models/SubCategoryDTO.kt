package com.farma.poc.features.home.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "subcategories")
@Parcelize
data class SubCategoryDTO(
    val name: String?= null,
    @PrimaryKey
    val id: Long?= null
): Parcelable
