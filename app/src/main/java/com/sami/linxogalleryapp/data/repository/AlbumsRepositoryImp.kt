package com.sami.linxogalleryapp.data.repository

import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.data.source.remote.dto.AlbumDto
import com.sami.linxogalleryapp.domain.repository.AlbumsRepository
import javax.inject.Inject

class AlbumsRepositoryImp @Inject constructor(
    private val api: GalleryApi
) : AlbumsRepository {

    override suspend fun getAlbums(): List<AlbumDto> = api.getAlbums()

}