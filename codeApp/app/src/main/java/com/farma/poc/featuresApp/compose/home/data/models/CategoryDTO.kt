package com.farma.poc.featuresApp.compose.home.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.utils.dto.ProductDTO
import kotlinx.parcelize.Parcelize

@Entity(tableName = "categories")
@Parcelize
data class CategoryDTO(
    var name: String? = null,
    var urlImage: String? = null,
    @PrimaryKey
    var id: Long? = null,
    var subCategories: List<SubCategoryDTO>? = null,
    var products: List<ProductDTO>? = null,
): Parcelable
