package com.davy.albumviewassignment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.davy.albumviewassignment.R
import kotlinx.android.synthetic.main.fragment_photo_detail.*

class PhotoDetailFragment : Fragment() {

    private val EXTRA_TITLE = "EXTRA_TITLE"
    private val EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL"

    private var title: String? = null
    private var imageUrl: String? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PhotoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_TITLE, param1)
                    putString(EXTRA_IMAGE_URL, param2)
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            title = it.getString(EXTRA_TITLE)
            imageUrl = it.getString(EXTRA_IMAGE_URL)
        }

        renderUi()
    }

    private fun renderUi () {

        Glide.with(this)
            .load(imageUrl)
            .into(imageViewThumbnail)

        textViewAlbumTitle.text = title
    }
}
