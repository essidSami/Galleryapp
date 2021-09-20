package com.sami.linxogalleryapp.presentation.album_list.state

import com.sami.linxogalleryapp.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val user: List<User>? = emptyList(),
    val error: String = ""
)