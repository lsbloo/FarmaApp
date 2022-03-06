package com.farma.poc.core.utils.converters

import androidx.room.TypeConverter
import com.farma.poc.core.utils.dto.ProductDTO
import com.farma.poc.home.data.models.HightLightsProductDTO
import com.farma.poc.home.data.models.SubCategoryDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class GeneralTypeConverter {
    private val gson: Gson = Gson()


    @TypeConverter
    fun saveSubCategoryDTO(subCategoryDTO: SubCategoryDTO): String {
        return gson.toJson(subCategoryDTO)
    }

    @TypeConverter
    fun restoreSubCategoryDTO(data: String): SubCategoryDTO? {
        if (data == null) return null
        val type: Type = object : TypeToken<SubCategoryDTO>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun saveSubCategoryListDTO(subCategoryDTO: List<SubCategoryDTO>): String {
        return gson.toJson(subCategoryDTO)
    }

    @TypeConverter
    fun restoreSubCategoryListDTO(data: String): List<SubCategoryDTO>? {
        if (data == null) return null
        val type: Type = object : TypeToken<List<SubCategoryDTO>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun saveProductDTO(productDTO: ProductDTO): String {
        return gson.toJson(productDTO)
    }

    @TypeConverter
    fun restoreProductDTO(data: String): ProductDTO? {
        if (data == null) return null
        val type: Type = object : TypeToken<ProductDTO>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun saveProductListDTO(productsDTO: List<ProductDTO>): String {
        return gson.toJson(productsDTO)
    }

    @TypeConverter
    fun restoreProductListDTO(data: String): List<ProductDTO>? {
        if (data == null) return null
        val type: Type = object : TypeToken<List<ProductDTO>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun saveHighLightProductDTO(hightLightsProductDTO: HightLightsProductDTO): String {
        return gson.toJson(hightLightsProductDTO)
    }

    @TypeConverter
    fun restoreHightLightProductDTO(data: String): HightLightsProductDTO? {
        if (data == null) return null
        return gson.fromJson(data, object : TypeToken<HightLightsProductDTO>() {}.type)
    }

}