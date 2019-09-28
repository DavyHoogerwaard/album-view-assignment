package com.davy.albumviewassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davy.albumviewassignment.dagger.DaggerApiComponent
import com.davy.albumviewassignment.retrofit.AlbumService
import com.davy.albumviewassignment.retrofit.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumViewModel : ViewModel() {

    @Inject
    lateinit var albumService: AlbumService

    private val disposable = CompositeDisposable()

    val albumList = MutableLiveData<List<Photo>>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getPhotoList() {

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

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
