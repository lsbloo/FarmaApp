package com.farmawebservice.delivery.geolocator

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import htk.geolocator.GeoLocaterApi.core.interfaces.Geocode
import org.springframework.stereotype.Component

@Component
class GeoLocation: Geocode {

     var apiKey: String="AIzaSyCLHoRNU4h-fvSgb4QtPaO4jXa9YiAtgc0"
    private val ctx: GeoApiContext  by lazy { GeoApiContext.Builder().apiKey(apiKey).build() }


    override fun geoCondingResult(address: String): String? {
        val results = GeocodingApi.geocode(ctx,address).await()
        fun isResults() = results.isNotEmpty()
        return when {
            isResults() -> getGsonInstance().toJson(results[0])
            else -> {""}
        }
    }

    override fun getGsonInstance(): Gson {
        return GsonBuilder().setPrettyPrinting().create()
    }


}