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

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        addInitialFragment(AlbumFragment())
    }

    private fun addInitialFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    private fun addFragment(fragment: Fragment) {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right)
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
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
