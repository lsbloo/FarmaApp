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
    var id: Long? = null,
    var name: String? = null,
    var value: Double? = null,
    var maxValue: Double? = null,
    var minValue: Double? = null,
    var description: String? = null,
    var subCategoryDTO: SubCategoryDTO? = null,
    var quantity: Int? = null,
    var productId: Long = 0,
    var image: String? = null,
    @Embedded(prefix = "drug_")
    var drug: DrugDTO? = null,
): BaseDTO(),Parcelable
