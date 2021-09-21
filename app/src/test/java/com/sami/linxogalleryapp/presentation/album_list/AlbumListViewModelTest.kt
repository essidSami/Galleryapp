package com.sami.linxogalleryapp.presentation.album_list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sami.linxogalleryapp.MainCoroutinesRule
import com.sami.linxogalleryapp.data.repository.AlbumsRepositoryImp
import com.sami.linxogalleryapp.data.repository.UserRepositoryImp
import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.domain.usecase.get_albums.GetAlbumsUseCase
import com.sami.linxogalleryapp.domain.usecase.get_user.GetUserUseCase
import com.sami.linxogalleryapp.utils.MockUtil
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class AlbumListViewModelTest {

    private lateinit var viewModel: AlbumListViewModel
    private lateinit var albumRepository: AlbumsRepositoryImp
    private lateinit var userRepository: UserRepositoryImp
    private val api: GalleryApi = mock()
    private lateinit var albumUseCase: GetAlbumsUseCase
    private lateinit var userUseCase: GetUserUseCase

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        albumRepository = AlbumsRepositoryImp(api)
        userRepository = UserRepositoryImp(api)
        albumUseCase = GetAlbumsUseCase(albumRepository)
        userUseCase = GetUserUseCase(userRepository)
        viewModel = AlbumListViewModel(albumUseCase, userUseCase)
    }

    @Test
    fun fetchAlbumListFromNetworkTest(): Unit = runBlocking {
        val mockData = MockUtil.mockAlbumList()
        //Given
        whenever(albumRepository.getAlbums()).thenReturn(mockData)
    }

    @Test
    fun fetchUserListFromNetworkTest(): Unit = runBlocking {
        val mockData = MockUtil.mockUserList()
        //Given
        whenever(userRepository.getUsers()).thenReturn(mockData)

    }
}