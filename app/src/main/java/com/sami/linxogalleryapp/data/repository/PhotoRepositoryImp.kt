package com.sami.linxogalleryapp.data.repository

import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.data.source.remote.dto.PhotoDto
import com.sami.linxogalleryapp.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImp @Inject constructor(
    private val api: GalleryApi
) : PhotoRepository {

    override suspend fun getPhotoByAlbum(albumId: Int): List<PhotoDto> =
        api.getPhotoByAlbum(albumId = albumId)
}