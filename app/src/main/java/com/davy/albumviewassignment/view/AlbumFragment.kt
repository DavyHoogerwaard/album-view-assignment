package com.davy.albumviewassignment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.AlbumService
import com.davy.albumviewassignment.retrofit.Photo
import com.davy.albumviewassignment.viewmodel.AlbumViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AlbumFragment : Fragment() {

    lateinit var viewModel: AlbumViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)
        viewModel.getPhotoList()

        observeViewModel()
    }

    fun observeViewModel() {

        viewModel.albumList.observe(this, Observer {
            Log.d("AlbumFragment", it.toString())
        })
    }
}
