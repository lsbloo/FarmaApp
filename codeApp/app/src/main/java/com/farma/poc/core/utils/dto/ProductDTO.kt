package com.farma.poc.core.utils.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.base.BaseDTO
import com.farma.poc.features.home.data.models.SubCategoryDTO
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ProductDTO(
    @PrimaryKey
    val id: Long? = null,
    val name: String? = null,
    val value: Double? = null,
    val maxValue: Double? = null,
    val minValue: Double? = null,
    val description: String? = null,
    val subCategoryDTO: SubCategoryDTO? = null,
    val quantity: Int? = null
): BaseDTO(),Parcelable
