@file:Suppress("SpellCheckingInspection")

package com.sami.linxogalleryapp.presentation.photo_list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sami.linxogalleryapp.MainCoroutinesRule
import com.sami.linxogalleryapp.data.repository.PhotoRepositoryImp
import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.domain.usecase.get_photos.GetPhotosUseCase
import com.sami.linxogalleryapp.utils.MockUtil.mockPhotoList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PhotoViewModelTest {

    private lateinit var viewModel: PhotoViewModel
    private lateinit var photoRepository: PhotoRepositoryImp
    private val api: GalleryApi = mock()
    private lateinit var photoUseCase: GetPhotosUseCase

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        photoRepository = PhotoRepositoryImp(api)
        photoUseCase = GetPhotosUseCase(photoRepository)
        viewModel = PhotoViewModel(photoUseCase)
    }

    @Test
    fun fetchPhotoListFromNetworkTest(): Unit = runBlocking {
        val mockData = mockPhotoList()
        whenever(photoRepository.getPhotoByAlbum(albumId = 1)).thenReturn(mockData)
    }
}