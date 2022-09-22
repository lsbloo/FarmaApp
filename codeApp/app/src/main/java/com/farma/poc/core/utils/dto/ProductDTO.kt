package com.farma.poc.core.utils.dto

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.base.BaseDTO
import com.farma.poc.featuresApp.compose.home.data.models.SubCategoryDTO
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ProductDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String? = null,
    val value: Double? = null,
    val maxValue: Double? = null,
    val minValue: Double? = null,
    val description: String? = null,
    val subCategoryDTO: SubCategoryDTO? = null,
    val quantity: Int? = null,
    val productId: Long = 0,
    val image: String? = null,
    @Embedded(prefix = "drug_")
    val drug: DrugDTO? = null,
): BaseDTO(),Parcelable
