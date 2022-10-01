package htk.geolocator.GeoLocaterApi.core.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddressComponents(
    @SerializedName("longName")
    @Expose
    val longName: String,
    @SerializedName("shortName")
    @Expose
    val shortName: String,
    @SerializedName("types")
    @Expose
    val types: List<String>
) {
}