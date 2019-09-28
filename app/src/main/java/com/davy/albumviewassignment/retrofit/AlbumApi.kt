package com.davy.albumviewassignment.retrofit

import io.reactivex.Single
import retrofit2.http.GET

interface AlbumApi {

    @GET("photos")
    fun getPhotoList() : Single<List<Photo>>
}
