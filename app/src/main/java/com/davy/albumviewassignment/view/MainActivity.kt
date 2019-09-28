package com.davy.albumviewassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.Photo

class MainActivity : AppCompatActivity(), AlbumFragment.Listener, PhotoListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(AlbumFragment())
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
    }

    override fun navigateToPhotoList(photoList: ArrayList<Photo>) {
        val fragment = PhotoListFragment.newInstance(photoList)
        addFragment(fragment)
    }

    override fun navigateToPhotoDetail(title: String, imageUrl: String) {
        val fragment = PhotoDetailFragment.newInstance(title, imageUrl)
        addFragment(fragment)
    }
}
