package com.farma.poc.featuresApp.compose.address.data.models

data class GeoLocateDTO(
    val addressComponents: List<AddressComponent>,
    val formattedAddress: String,
    val geometry: Geometry,
    val types: List<String>,
    val partitalMath: Boolean,
    val placeID: String
) {

}

data class AddressComponent (
    val longName: String,
    val shortName: String,
    val types: List<String>
)

data class Geometry (
    val bounds: Bounds,
    val location: Location,
    val locationType: String,
    val viewport: Bounds
)

data class Bounds (
    val northeast: Location,
    val southwest: Location
)

data class Location (
    val lat: Double,
    val lng: Double
)