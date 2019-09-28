package com.davy.albumviewassignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.Album
import kotlinx.android.synthetic.main.item_album_recyclerview.view.*

class AlbumRecyclerViewAdapter(var albumList: ArrayList<Album>): RecyclerView.Adapter<AlbumRecyclerViewAdapter.AlbumViewHolder>() {

    fun updateAlbumList(newAlbumList: List<Album>) {

        albumList.clear()
        albumList.addAll(newAlbumList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        return albumList.size
    }


    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    inner class AlbumViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val albumTitle = view.title

        fun bind (album: Album) {

            albumTitle.text = album.albumId.toString()
        }
    }
}
