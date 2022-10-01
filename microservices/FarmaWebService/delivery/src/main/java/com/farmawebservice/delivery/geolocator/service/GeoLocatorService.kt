package com.farmawebservice.delivery.geolocator.service

import com.farmawebservice.delivery.geolocator.GeoLocation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GeoLocatorService () : GeoLocationService {

    @Autowired
    private lateinit var geoLocation: GeoLocation


    override fun getLocationByAddress(address: String): String? {
        return geoLocation.geoCondingResult(address)
    }


}