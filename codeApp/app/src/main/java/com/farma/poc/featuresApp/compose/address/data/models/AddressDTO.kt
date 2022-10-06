package com.farma.poc.featuresApp.compose.address.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farma.poc.core.base.BaseDTO
import com.google.gson.annotations.SerializedName
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
    var description: String? = null,
    var isPrincipal: Boolean? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var address_id: Long? = null,
) : BaseDTO(), Parcelable {}
fun AddressDTO.setAddressId(id: Long) {
    this.address_id = id
}