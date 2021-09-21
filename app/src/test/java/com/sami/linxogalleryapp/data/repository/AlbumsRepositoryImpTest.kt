package com.sami.linxogalleryapp.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sami.linxogalleryapp.MainCoroutinesRule
import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.domain.usecase.get_albums.GetAlbumsUseCase
import com.sami.linxogalleryapp.domain.usecase.get_user.GetUserUseCase
import com.sami.linxogalleryapp.utils.MockUtil
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class AlbumsRepositoryImpTest {

    private lateinit var albumUseCase: GetAlbumsUseCase
    private lateinit var repository: AlbumsRepositoryImp
    private val api: GalleryApi = mock()

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        repository = AlbumsRepositoryImp(api)
        albumUseCase = GetAlbumsUseCase(repository)
    }

    @Test
    fun fetchUserListFromNetworkTest(): Unit = runBlocking {
        whenever(api.getAlbums()).thenReturn(Response.success(MockUtil.mockAlbumList()).body())
    }
}