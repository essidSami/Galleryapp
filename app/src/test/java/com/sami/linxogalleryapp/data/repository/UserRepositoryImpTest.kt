package com.sami.linxogalleryapp.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sami.linxogalleryapp.MainCoroutinesRule
import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.domain.usecase.get_photos.GetPhotosUseCase
import com.sami.linxogalleryapp.domain.usecase.get_user.GetUserUseCase
import com.sami.linxogalleryapp.utils.MockUtil
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class UserRepositoryImpTest {

    private lateinit var userUseCase: GetUserUseCase
    private lateinit var repository: UserRepositoryImp
    private val api: GalleryApi = mock()

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        repository = UserRepositoryImp(api)
        userUseCase = GetUserUseCase(repository)
    }

    @Test
    fun fetchUserListFromNetworkTest(): Unit = runBlocking {
        whenever(api.getUsers()).thenReturn(Response.success(MockUtil.mockUserList()).body())
    }
}