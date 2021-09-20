package com.sami.linxogalleryapp.di

import com.sami.linxogalleryapp.data.repository.AlbumsRepositoryImp
import com.sami.linxogalleryapp.data.repository.PhotoRepositoryImp
import com.sami.linxogalleryapp.data.repository.UserRepositoryImp
import com.sami.linxogalleryapp.data.source.remote.GalleryApi
import com.sami.linxogalleryapp.domain.repository.AlbumsRepository
import com.sami.linxogalleryapp.domain.repository.PhotoRepository
import com.sami.linxogalleryapp.domain.repository.UserRepository
import com.sami.linxogalleryapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getProviderGalleryApi(): GalleryApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GalleryApi::class.java)

    @Provides
    @Singleton
    fun getProviderAlbumsRepository(api: GalleryApi): AlbumsRepository =
        AlbumsRepositoryImp(api)

    @Provides
    @Singleton
    fun getProviderUserRepository(api: GalleryApi): UserRepository =
        UserRepositoryImp(api)

    @Provides
    @Singleton
    fun getProviderPhotoRepository(api: GalleryApi): PhotoRepository =
        PhotoRepositoryImp(api)
}