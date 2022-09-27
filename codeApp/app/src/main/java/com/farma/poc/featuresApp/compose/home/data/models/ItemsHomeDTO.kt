package com.farma.poc.featuresApp.compose.home.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.utils.dto.ProductDTO
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ItemsHomeDTO(
    var categories: List<CategoryDTO>? = null,
    var productsHighLight: List<ProductDTO>? = null,
    var highLights: List<ProductDTO>? = null,
    @PrimaryKey
    var id: Long = 1,
) : Parcelable {
}
