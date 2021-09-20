package com.sami.linxogalleryapp.domain.repository

import com.sami.linxogalleryapp.data.source.remote.dto.UserDto

interface UserRepository {

    suspend fun getUsers() : List<UserDto>
}