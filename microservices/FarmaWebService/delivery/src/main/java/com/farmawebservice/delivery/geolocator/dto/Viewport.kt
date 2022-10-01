package htk.geolocator.GeoLocaterApi.core.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Viewport(
    @SerializedName("northeast")
    @Expose
    val northeast: Northeast,

    @SerializedName("southwest")
    @Expose
    val southwest: Southwest

)
