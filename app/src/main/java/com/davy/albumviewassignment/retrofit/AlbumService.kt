package com.davy.albumviewassignment.retrofit

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AlbumService {

    private val BASE_URL = "https://jsonplaceholder.typicode.com"
    private val albumApi: AlbumApi

    init {
        albumApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AlbumApi::class.java)
    }

    fun getPhotoList(): Single<List<Photo>> {
        return albumApi.getPhotoList()
    }
}
