package com.sami.linxogalleryapp.presentation.album_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.linxogalleryapp.domain.model.User
import com.sami.linxogalleryapp.domain.usecase.get_albums.GetAlbumsUseCase
import com.sami.linxogalleryapp.domain.usecase.get_user.GetUserUseCase
import com.sami.linxogalleryapp.presentation.album_list.state.AlbumListState
import com.sami.linxogalleryapp.presentation.album_list.state.UserState
import com.sami.linxogalleryapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _stateAlbums = MutableLiveData<AlbumListState>()
    val stateAlbums: LiveData<AlbumListState> = _stateAlbums

    private val _stateUsers = MutableLiveData<UserState>()
    val stateUsers: MutableList<User> = mutableListOf()


    init {
        getUserList()
        getAlbumList()
    }

    fun getAlbumList() {
        getAlbumsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateAlbums.value = AlbumListState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    val albumList = result.data?.sortedBy { it.title } ?: emptyList()
                    albumList.let {
                        albumList.forEachIndexed { index, album ->
                            stateUsers.filter { it.id == album.userId }.map {
                                albumList[index].userName = it.username
                            }
                        }
                    }
                    _stateAlbums.value = AlbumListState(
                        albums = albumList
                    )
                }
                is Resource.Error -> {
                    _stateAlbums.value = AlbumListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

            }
        }.launchIn(viewModelScope)
    }

    fun getUserList() {
        getUserUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateUsers.value = UserState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    stateUsers.addAll(result.data ?: emptyList())

                }
                is Resource.Error -> {
                    _stateUsers.value = UserState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

            }
        }.launchIn(viewModelScope)
    }
}