package com.davy.albumviewassignment.retrofit

import com.davy.albumviewassignment.dagger.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class AlbumService {

    @Inject
    lateinit var albumApi: AlbumApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getPhotoList(): Single<List<Photo>> {
        return albumApi.getPhotoList()
    }
}
