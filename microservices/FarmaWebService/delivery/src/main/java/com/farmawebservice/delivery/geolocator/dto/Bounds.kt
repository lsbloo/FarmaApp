package htk.geolocator.GeoLocaterApi.core.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Bounds(
    @SerializedName("northeast")
    @Expose
    val northeast: Northeast,
    @SerializedName("southwest")
    @Expose
    val southwest: Southwest


)
