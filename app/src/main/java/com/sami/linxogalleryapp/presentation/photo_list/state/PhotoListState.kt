package com.sami.linxogalleryapp.presentation.photo_list.state

import com.sami.linxogalleryapp.domain.model.Photo

data class PhotoListState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String = ""
)