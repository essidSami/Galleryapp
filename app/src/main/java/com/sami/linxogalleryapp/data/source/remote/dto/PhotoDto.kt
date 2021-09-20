package com.sami.linxogalleryapp.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.sami.linxogalleryapp.domain.model.Photo

data class PhotoDto(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

fun PhotoDto.toPhoto(): Photo {
    return Photo(
        albumId = albumId,
        id = id,
        thumbnailUrl = thumbnailUrl,
        title = title,
        url = url
    )
}
