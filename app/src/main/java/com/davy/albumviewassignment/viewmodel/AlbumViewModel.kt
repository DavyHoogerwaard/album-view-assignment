package com.davy.albumviewassignment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davy.albumviewassignment.retrofit.AlbumService
import com.davy.albumviewassignment.retrofit.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AlbumViewModel : ViewModel() {

    private val albumService = AlbumService()
    private val disposable = CompositeDisposable()

    val albumList = MutableLiveData<List<Photo>>()

    fun getPhotoList() {
        disposable.add(
            albumService.getPhotoList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Photo>>() {

                    override fun onSuccess(value: List<Photo>?) {
                        albumList.value = value
                        Log.d("AlbumViewModel", value?.size.toString())
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("AlbumViewModel", e.toString())
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
