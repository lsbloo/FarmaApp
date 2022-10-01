package com.farmawebservice.delivery.geolocator.controller

import com.farmawebservice.delivery.geolocator.service.GeoLocatorService
import com.google.gson.GsonBuilder
import htk.geolocator.GeoLocaterApi.core.dto.GeoLocationDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/geolocation")
class GeoLocationController {


    @Autowired
    private lateinit var serviceGeoLocation: GeoLocatorService

    @GetMapping
    fun getLocationByAddress(@RequestParam("address") address: String): GeoLocationDTO? {
        return GsonBuilder().create().fromJson(serviceGeoLocation.getLocationByAddress(address), GeoLocationDTO::class.java)
    }
}