package com.sami.linxogalleryapp.data.source.remote

import com.sami.linxogalleryapp.data.source.remote.dto.AlbumDto
import com.sami.linxogalleryapp.data.source.remote.dto.PhotoDto
import com.sami.linxogalleryapp.data.source.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GalleryApi {

    @GET("/albums")
    suspend fun getAlbums(): List<AlbumDto>

    @GET("/users/")
    suspend fun getUsers(): List<UserDto>

    @GET("/albums/{albumId}/photos")
    suspend fun getPhotoByAlbum(
        @Path("albumId") albumId: Int): List<PhotoDto>
}