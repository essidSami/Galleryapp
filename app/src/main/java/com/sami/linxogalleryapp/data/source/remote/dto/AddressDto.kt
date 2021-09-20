package com.sami.linxogalleryapp.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName("city")
    val city: String,
    @SerializedName("geo")
    val geo: GeoDto,
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("zipcode")
    val zipcode: String
)