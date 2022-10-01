package htk.geolocator.GeoLocaterApi.core.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeoLocationDTO(

    @SerializedName("addressComponents")
    @Expose
    val addressComponents: List<AddressComponents>,

    @SerializedName("formattedAddress")
    @Expose
    val formattedAddress: String,
    @SerializedName("geometry")
    @Expose
    val geometry: Geometry,

    @SerializedName("types")
    @Expose
    val types: List<String>,

    @SerializedName("partialMatch")
    @Expose
    val partitalMath: Boolean,

    @SerializedName("placeId")
    @Expose
    val placeId: String

)