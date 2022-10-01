package htk.geolocator.GeoLocaterApi.core.interfaces

import com.google.gson.Gson
import com.google.maps.GeoApiContext
import com.google.maps.model.GeocodingResult

interface Geocode {


    fun geoCondingResult(address: String): String?

    fun getGsonInstance(): Gson


}