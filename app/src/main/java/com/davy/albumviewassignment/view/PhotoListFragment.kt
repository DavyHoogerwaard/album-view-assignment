package com.davy.albumviewassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.Photo
import kotlinx.android.synthetic.main.fragment_photo_list.*

class PhotoListFragment : Fragment() {

    private lateinit var photoListAdapter: PhotoListRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecyclerView()
    }

    private fun createRecyclerView() {

        val photo = Photo(2, 2, "title", "https://via.placeholder.com/600/92c952", "https://via.placeholder.com/150/92c952")
        val list: ArrayList<Photo> = arrayListOf(photo)

        photoListAdapter = PhotoListRecyclerViewAdapter(list, listener = { photoId -> recyclerViewClicked(photoId) })

        photoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }
    }

    private fun recyclerViewClicked(photoId: Int) {

    }
}
