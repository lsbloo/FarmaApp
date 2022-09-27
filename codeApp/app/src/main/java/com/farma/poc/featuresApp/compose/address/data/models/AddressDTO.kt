package com.farma.poc.featuresApp.compose.address.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.base.BaseDTO
import kotlinx.parcelize.Parcelize

@Entity(tableName = "categories")
@Parcelize
class AddressDTO(
    private var cep: String? = null,
    private var latitude: Double? = null,
    private var longitude: Double? = null,
    private var number: String? = null,
    private var state: String? = null,
    private var city: String? = null,
    private var street: String? = null,

    @PrimaryKey(autoGenerate = true)
    private var id: Long? = null,
) : BaseDTO(), Parcelable {
}