package com.davy.albumviewassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.Photo
import kotlinx.android.synthetic.main.item_album_recyclerview.view.*

class AlbumRecyclerViewAdapter(var photoList: ArrayList<Photo>): RecyclerView.Adapter<AlbumRecyclerViewAdapter.PhotoViewHolder>() {

    fun updatePhotoList(newPhotoList: List<Photo>) {

        photoList.clear()
        photoList.addAll(newPhotoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return photoList.size
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    inner class PhotoViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val photoTitle = view.title

        fun bind (photo: Photo) {

            photoTitle.text = photo.albumId.toString()
        }
    }
}
