package com.sami.linxogalleryapp.domain.model

import java.io.Serializable

data class Album(
    val id: Int,
    val title: String,
    val userId: Int,
    var userName: String?
) : Serializable