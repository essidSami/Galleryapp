@file:Suppress("SpellCheckingInspection")
package com.sami.linxogalleryapp.data.repository

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.sami.linxogalleryapp.MainCoroutinesRule
import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.data.source.remote.dto.PhotoDto
import com.sami.linxogalleryapp.domain.usecase.get_photos.GetPhotosUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import com.nhaarman.mockitokotlin2.verify
import com.sami.linxogalleryapp.util.Resource
import com.sami.linxogalleryapp.utils.MockUtil.mockPhotoList
import kotlinx.coroutines.flow.flowOf
import kotlin.time.ExperimentalTime

class PhotoRepositoryImpTest {

    private lateinit var photoUseCase: GetPhotosUseCase
    private lateinit var repository: PhotoRepositoryImp
    private val api: GalleryApi = mock()

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        repository = PhotoRepositoryImp(api)
        photoUseCase = GetPhotosUseCase(repository)
    }

    @Test
    fun fetchPhotoListFromNetworkTest(): Unit = runBlocking {
        whenever(api.getPhotoByAlbum(1)).thenReturn(Response.success(mockPhotoList()).body())
    }
}