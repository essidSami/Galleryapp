package com.sami.linxogalleryapp.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class CompanyDto(
    @SerializedName("bs")
    val domaine: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("name")
    val name: String
)