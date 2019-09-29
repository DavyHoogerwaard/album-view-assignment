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

/**
 * Fragment that shows a list of all the photos in the selected album
 */
class PhotoListFragment : Fragment() {

    private val EXTRA_PHOTO_LIST = "EXTRA_PHOTO_LIST"

    private lateinit var photoList: ArrayList<Photo>
    private lateinit var photoListAdapter: PhotoListRecyclerViewAdapter
    private lateinit var listener: Listener

    companion object {

        @JvmStatic
        fun newInstance(photoList: ArrayList<Photo>) =
            PhotoListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_PHOTO_LIST, photoList)
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { photoList = it.getParcelableArrayList<Photo>(EXTRA_PHOTO_LIST) }
        createRecyclerView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Listener) listener = context
    }

    private fun createRecyclerView() {

        photoListAdapter = PhotoListRecyclerViewAdapter(photoList, listener = { title, imageUrl -> recyclerViewClicked(title, imageUrl) })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }
    }

    private fun recyclerViewClicked(title: String, imageUrl: String) {

        listener.navigateToPhotoDetail(title, imageUrl)
    }

    interface Listener {

        /**
         * Callback for when the user presses on a photo row
         */
        fun navigateToPhotoDetail(title: String, imageUrl: String)
    }
}
