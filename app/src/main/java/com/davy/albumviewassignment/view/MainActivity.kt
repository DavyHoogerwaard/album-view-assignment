package com.davy.albumviewassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.davy.albumviewassignment.R

class MainActivity : AppCompatActivity(), AlbumFragment.Listener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(AlbumFragment())
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
    }

    override fun navigateToPhotoList(albumId: Int) {
        addFragment(PhotoListFragment())
        Log.d("MainActivity", albumId.toString())
    }
}
