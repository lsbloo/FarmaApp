package htk.geolocator.GeoLocaterApi.core.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Northeast(
    @SerializedName("lat")
    @Expose
    val lat: Double,
    @SerializedName("lng")
    @Expose
    val lng: Double
)
