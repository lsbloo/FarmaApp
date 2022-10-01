package com.farmawebservice.delivery.geolocator.service

interface GeoLocationService {
    fun getLocationByAddress(address: String): String?
}