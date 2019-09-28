package com.davy.albumviewassignment.dagger

import com.davy.albumviewassignment.retrofit.AlbumApi
import com.davy.albumviewassignment.retrofit.AlbumService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    @Provides
    fun provideAlbumApi(): AlbumApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AlbumApi::class.java)
    }

    @Provides
    fun provideAlbumService(): AlbumService {
        return AlbumService()
    }
}
