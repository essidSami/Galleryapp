package com.sami.linxogalleryapp.domain.usecase.get_user

import com.sami.linxogalleryapp.data.source.remote.dto.toUser
import com.sami.linxogalleryapp.domain.model.User
import com.sami.linxogalleryapp.domain.repository.UserRepository
import com.sami.linxogalleryapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading<List<User>>())
            val user = repository.getUsers().map { it.toUser() }
            emit(Resource.Success<List<User>>(user))
        } catch (e: HttpException) {
            emit(Resource.Error<List<User>>(""))
        } catch (e: IOException) {
            emit(Resource.Error<List<User>>(""))
        }
    }
}