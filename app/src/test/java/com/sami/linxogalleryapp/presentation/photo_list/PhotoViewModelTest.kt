@file:Suppress("SpellCheckingInspection")

package com.sami.linxogalleryapp.presentation.photo_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sami.linxogalleryapp.MainCoroutinesRule
import com.sami.linxogalleryapp.data.repository.PhotoRepositoryImp
import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.data.source.remote.dto.toPhoto
import com.sami.linxogalleryapp.domain.usecase.get_photos.GetPhotosUseCase
import com.sami.linxogalleryapp.presentation.photo_list.state.PhotoListState
import com.sami.linxogalleryapp.utils.MockUtil.mockPhotoList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PhotoViewModelTest {

    private lateinit var viewModel: PhotoViewModel
    private lateinit var photoRepository: PhotoRepositoryImp
    private val api: GalleryApi = mock()
    private lateinit var photoUseCase: GetPhotosUseCase
    @Mock
    private lateinit var photoListState: Observer<PhotoListState>

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        photoRepository = PhotoRepositoryImp(api)
        photoUseCase = GetPhotosUseCase(photoRepository)
        viewModel = PhotoViewModel(photoUseCase).apply {
            statePhotos.observeForever(photoListState)
        }
    }

    @Test
    fun fetchPhotoListFromNetworkTest(): Unit = runBlocking {
        //Given
        val mockData = mockPhotoList()
        whenever(photoRepository.getPhotoByAlbum(albumId = 1)).thenReturn(mockData)
        //When
        viewModel.getPhotoList(1)
        //Then
        verify(photoListState).onChanged(PhotoListState(isLoading = true))
        verify(photoListState).onChanged(PhotoListState(photos = mockData.map { it.toPhoto() }))
    }
}