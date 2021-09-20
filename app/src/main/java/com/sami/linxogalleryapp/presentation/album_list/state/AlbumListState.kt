package com.sami.linxogalleryapp.presentation.album_list.state

import com.sami.linxogalleryapp.domain.model.Album

data class AlbumListState(
    val isLoading: Boolean = false,
    val albums: List<Album> = emptyList(),
    val error: String = ""
)