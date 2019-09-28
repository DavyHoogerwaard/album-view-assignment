package com.davy.albumviewassignment.view

import android.content.Context
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

    private var photoList: ArrayList<Photo> = arrayListOf()
    private lateinit var photoListAdapter: PhotoListRecyclerViewAdapter
    private lateinit var listener: Listener

    companion object {

        val EXTRA_PHOTO_LIST = "EXTRA_PHOTO_LIST"

        fun newInstance(photoList: ArrayList<Photo>): PhotoListFragment {

            val bundle = Bundle()
            bundle.putSerializable(EXTRA_PHOTO_LIST, photoList)
            val fragment = PhotoListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoList = arguments?.getSerializable(EXTRA_PHOTO_LIST) as ArrayList<Photo>

        createRecyclerView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Listener) listener = context
    }

    private fun createRecyclerView() {

        photoListAdapter = PhotoListRecyclerViewAdapter(photoList, listener = { title, imageUrl -> recyclerViewClicked(title, imageUrl) })

        photoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }
    }

    private fun recyclerViewClicked(title: String, imageUrl: String) {

        listener.navigateToPhotoDetail(title, imageUrl)
    }

    interface Listener {

        fun navigateToPhotoDetail(title: String, imageUrl: String)
    }
}
