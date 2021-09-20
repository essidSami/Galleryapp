package com.sami.linxogalleryapp.domain.repository

import com.sami.linxogalleryapp.data.source.remote.dto.AlbumDto

interface AlbumsRepository {
    suspend fun getAlbums(): List<AlbumDto>
}