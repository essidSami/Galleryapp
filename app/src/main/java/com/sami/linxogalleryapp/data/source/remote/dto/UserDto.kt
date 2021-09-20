package com.sami.linxogalleryapp.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.sami.linxogalleryapp.domain.model.User

data class UserDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("address")
    val address: AddressDto,
    @SerializedName("company")
    val company: CompanyDto,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("website")
    val website: String
)

fun UserDto.toUser() : User {
    return User(
        id = id,
        username = username
    )
}