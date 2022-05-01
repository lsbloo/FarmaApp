package com.farma.poc.features.home.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.farma.poc.core.utils.converters.GeneralTypeConverter
import com.farma.poc.core.utils.dto.ProductDTO
import kotlinx.parcelize.Parcelize

@Entity(tableName = "hightlight_products")
@Parcelize
data class HightLightsProductDTO(
    @PrimaryKey
    val id: Long? = null,
    @TypeConverters(GeneralTypeConverter::class)
    val productsHightLight: List<ProductDTO>? = null
): Parcelable
