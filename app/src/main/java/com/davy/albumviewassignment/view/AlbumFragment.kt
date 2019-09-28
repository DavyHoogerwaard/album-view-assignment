package com.davy.albumviewassignment.view

import android.content.Context
import android.os.Bundle
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

    private lateinit var viewModel: AlbumViewModel
    private lateinit var albumAdapter: AlbumRecyclerViewAdapter
    private lateinit var listener: Listener

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Listener) listener = context
    }

    private fun createRecyclerView() {

        albumAdapter = AlbumRecyclerViewAdapter(listener = { albumId -> recyclerViewClicked(albumId) })

        albumRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
        }
    }

    private fun observeViewModel() {

        viewModel.albumList.observe(this, Observer { photos ->
            photos?.let {
                val presentableList = viewModel.createPresentableAlbumList(it)
                albumAdapter.updateAlbumList(presentableList)
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loadingProgressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }

    private fun recyclerViewClicked(albumId: Int) {
        listener.navigateToPhotoList(albumId)
    }

    interface Listener {

        fun navigateToPhotoList(albumId: Int)
    }
}
