package com.sami.linxogalleryapp.domain.usecase.get_albums

import com.sami.linxogalleryapp.data.source.remote.dto.toAlbum
import com.sami.linxogalleryapp.domain.model.Album
import com.sami.linxogalleryapp.domain.repository.AlbumsRepository
import com.sami.linxogalleryapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {

    operator fun invoke(): Flow<Resource<List<Album>>> = flow {
        try {
            emit(Resource.Loading<List<Album>>())
            val albumList = repository.getAlbums().map { it.toAlbum() }
            emit(Resource.Success<List<Album>>(albumList))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Album>>("An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error<List<Album>>("Couldn't reach server. Check your internet connection."))
        }
    }
}