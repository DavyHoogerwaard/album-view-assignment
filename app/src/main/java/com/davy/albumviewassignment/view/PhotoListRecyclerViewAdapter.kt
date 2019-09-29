package com.davy.albumviewassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.Photo
import kotlinx.android.synthetic.main.item_photolist_recyclerview.view.*

/**
 * Adapter for the PhotoListFragment
 */
class PhotoListRecyclerViewAdapter(var photoList: ArrayList<Photo>, val listener : (String, String) -> Unit): RecyclerView.Adapter<PhotoListRecyclerViewAdapter.PhotoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        return PhotoListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photolist_recyclerview, parent, false))
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        holder.bind(photoList[position], listener)
    }

    inner class PhotoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val imageViewPhoto = itemView.imageViewPhoto
        private val photoTitle = itemView.textViewTitle

        fun bind (photoList: Photo, listener:(String, String) -> Unit) {

            Glide.with(itemView)
                .load(photoList.thumbnailUrl)
                .into(imageViewPhoto)

            photoTitle.text = photoList.title

            itemView.setOnClickListener { listener(photoList.title, photoList.url) }
        }
    }
}
