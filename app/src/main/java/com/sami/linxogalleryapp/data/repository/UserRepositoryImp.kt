package com.sami.linxogalleryapp.data.repository

import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.data.source.remote.dto.AlbumDto
import com.sami.linxogalleryapp.data.source.remote.dto.UserDto
import com.sami.linxogalleryapp.domain.repository.AlbumsRepository
import com.sami.linxogalleryapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val api: GalleryApi
) : UserRepository {

    override suspend fun getUsers(): List<UserDto> =
        api.getUsers()
}