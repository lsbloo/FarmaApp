package com.farma.poc.featuresApp.compose.home.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.utils.dto.ProductDTO
import kotlinx.parcelize.Parcelize

@Entity(tableName = "categories")
@Parcelize
data class CategoryDTO(
    val name: String? = null,
    val urlImage: String? = null,
    @PrimaryKey
    val id: Long? = null,
    val subCategories: List<SubCategoryDTO>? = null,
    val products: List<ProductDTO>? = null,
): Parcelable
