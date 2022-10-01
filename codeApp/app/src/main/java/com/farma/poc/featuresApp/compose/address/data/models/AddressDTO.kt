package com.farma.poc.featuresApp.compose.address.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.base.BaseDTO
import kotlinx.parcelize.Parcelize

@Entity(tableName = "addresses")
@Parcelize
class AddressDTO(
    var cep: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var number: String? = null,
    var state: String? = null,
    var city: String? = null,
    var street: String? = null,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
) : BaseDTO(), Parcelable {
}