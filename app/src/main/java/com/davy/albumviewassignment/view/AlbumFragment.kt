package com.davy.albumviewassignment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.AlbumService
import com.davy.albumviewassignment.retrofit.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AlbumFragment : Fragment() {

    private val albumService = AlbumService()
    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposable.add(
            albumService.getPhotoList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Photo>>() {

                    override fun onSuccess(value: List<Photo>?) {
                        Log.d("AlbumFragment", value?.size.toString())
                    }

                    override fun onError(e: Throwable?) {
                        Log.d("AlbumFragment", e.toString())
                    }
                })
        )

    }
}
