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

    /**
     * Get a list of photos. The list is emitted a single time
     */
    fun getPhotoList(): Single<List<Photo>> {
        return albumApi.getPhotoList()
    }
}
