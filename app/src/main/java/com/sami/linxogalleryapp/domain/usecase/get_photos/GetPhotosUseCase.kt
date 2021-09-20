package com.sami.linxogalleryapp.domain.usecase.get_photos

import com.sami.linxogalleryapp.data.source.remote.dto.toPhoto
import com.sami.linxogalleryapp.domain.model.Photo
import com.sami.linxogalleryapp.domain.repository.PhotoRepository
import com.sami.linxogalleryapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {

    operator fun invoke(albumId: Int): Flow<Resource<List<Photo>>> = flow {
        try {
            emit(Resource.Loading<List<Photo>>())
            val photo = repository.getPhotoByAlbum(albumId = albumId).map { it.toPhoto() }
            emit(Resource.Success<List<Photo>>(photo))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Photo>>(""))
        } catch (e: IOException) {
            emit(Resource.Error<List<Photo>>(""))
        }
    }
}