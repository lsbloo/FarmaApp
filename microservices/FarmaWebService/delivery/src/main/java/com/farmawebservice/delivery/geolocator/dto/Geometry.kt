package htk.geolocator.GeoLocaterApi.core.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Geometry(
    @SerializedName("bounds")
    @Expose
    val bounds: Bounds,

    @SerializedName("location")
    @Expose
    val location: Location,

    @SerializedName("locationType")
    @Expose
    val locationType: String,

    @SerializedName("viewport")
    @Expose
    val viewport: Viewport
)
