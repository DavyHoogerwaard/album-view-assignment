package com.davy.albumviewassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davy.albumviewassignment.dagger.DaggerApiComponent
import com.davy.albumviewassignment.retrofit.Album
import com.davy.albumviewassignment.retrofit.AlbumService
import com.davy.albumviewassignment.retrofit.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel for the AlbumListFragment
 */
class AlbumListViewModel : ViewModel() {

    @Inject
    lateinit var albumService: AlbumService

    private val disposable = CompositeDisposable()

    val albumList = MutableLiveData<List<Photo>>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    /**
     * Fetch the data from the service
     */
    fun fetchData() {

        loading.value = true

        disposable.add(
            albumService.getPhotoList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Photo>>() {

                    override fun onSuccess(value: List<Photo>?) {
                        albumList.value = value
                        loading.value = false
                    }

                    override fun onError(error: Throwable?) {
                        loading.value = false
                        Log.d("AlbumViewModel", error.toString())
                    }
                })
        )
    }

    /**
     * Create a new list for the AlbumRecyclerViewAdapter
     */
    fun createPresentableAlbumList(photoList: List<Photo>): List<Album> {

        val presentableList: ArrayList<Album> = arrayListOf()
        var currentId: Int = -1

        for (photo in photoList) {

            if (photo.albumId != currentId) {

                presentableList.add(Album(photo.albumId, photo.thumbnailUrl))
                currentId = photo.albumId
            }
        }

        return presentableList
    }

    /**
     * Create a new list for the PhotoListRecyclerAdapter
     */
    fun createPresentablePhotoList(albumId: Int): ArrayList<Photo> {

        val presentableList: ArrayList<Photo> = arrayListOf()

        for(photo in albumList.value.orEmpty()) {
            if(photo.albumId == albumId) presentableList.add(photo)
        }

        return presentableList
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
