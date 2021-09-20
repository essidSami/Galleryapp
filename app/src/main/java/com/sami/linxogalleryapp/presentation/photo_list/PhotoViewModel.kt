package com.sami.linxogalleryapp.presentation.photo_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.linxogalleryapp.domain.usecase.get_photos.GetPhotosUseCase
import com.sami.linxogalleryapp.presentation.photo_list.state.PhotoListState
import com.sami.linxogalleryapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
): ViewModel(){

    private val _statePhotos = MutableLiveData<PhotoListState>()
    val statePhotos: LiveData<PhotoListState> = _statePhotos

    fun getPhotoList(albumId: Int) {
        getPhotosUseCase(albumId = albumId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _statePhotos.value = PhotoListState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _statePhotos.value = PhotoListState(
                        photos = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _statePhotos.value = PhotoListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

            }
        }.launchIn(viewModelScope)
    }
}