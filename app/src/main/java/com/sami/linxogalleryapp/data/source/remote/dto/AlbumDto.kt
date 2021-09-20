package com.sami.linxogalleryapp.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.sami.linxogalleryapp.domain.model.Album

data class AlbumDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)

fun AlbumDto.toAlbum(): Album{
    return Album(
        id = id,
        title = title,
        userId = userId,
        userName = ""
    )
}