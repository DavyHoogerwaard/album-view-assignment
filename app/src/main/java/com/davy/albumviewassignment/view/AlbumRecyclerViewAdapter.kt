package com.davy.albumviewassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.Album
import kotlinx.android.synthetic.main.item_album_recyclerview.view.*

class AlbumRecyclerViewAdapter(var albumList: ArrayList<Album> = ArrayList(), val listener : (Int) -> Unit): RecyclerView.Adapter<AlbumRecyclerViewAdapter.AlbumViewHolder>() {

    fun updateAlbumList(newAlbumList: List<Album>) {

        albumList.clear()
        albumList.addAll(newAlbumList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_recyclerview, parent, false))
    }

    override fun getItemCount(): Int = albumList.size


    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albumList[position], listener)
    }

    inner class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val albumThumbNail = itemView.imageViewThumbnail
        private val albumTitle = itemView.title

        fun bind (album: Album, listener:(Int) -> Unit) {

            Glide.with(itemView)
                .load(album.thumbnailUrl)
                .into(albumThumbNail)

            albumTitle.text = album.albumId.toString()
            itemView.setOnClickListener { listener(album.albumId) }
        }
    }
}
