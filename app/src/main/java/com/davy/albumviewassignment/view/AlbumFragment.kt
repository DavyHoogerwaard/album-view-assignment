package com.davy.albumviewassignment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.fragment_album.*

class AlbumFragment : Fragment() {

    lateinit var viewModel: AlbumViewModel
    private val albumAdapter = AlbumRecyclerViewAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)
        viewModel.getPhotoList()

        createRecyclerView()
        observeViewModel()
    }

    fun createRecyclerView() {
        albumRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
        }
    }

    fun observeViewModel() {

        viewModel.albumList.observe(this, Observer {
            albumAdapter.updatePhotoList(it)
            Log.d("AlbumFragment", it.toString())
        })
    }
}
