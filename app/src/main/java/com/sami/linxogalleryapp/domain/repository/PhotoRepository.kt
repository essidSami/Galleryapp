package com.sami.linxogalleryapp.domain.repository

import com.sami.linxogalleryapp.data.source.remote.dto.PhotoDto

interface PhotoRepository {

    suspend fun getPhotoByAlbum(albumId: Int) : List<PhotoDto>
}