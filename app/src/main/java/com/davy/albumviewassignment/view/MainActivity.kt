package com.davy.albumviewassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.davy.albumviewassignment.R
import com.davy.albumviewassignment.retrofit.Photo

class MainActivity : AppCompatActivity(), AlbumListFragment.Listener, PhotoListFragment.Listener {

    private val TAG_ALBUM_FRAGMENT = "TAG_ALBUM_FRAGMENT"
    private val TAG_PHOTO_FRAGMENT = "TAG_PHOTO_FRAGMENT"
    private val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addInitialFragment()
        setToolbarItems(false, "Albums")
        supportFragmentManager.addOnBackStackChangedListener { updateToolbar() }
    }

    private fun addInitialFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, AlbumListFragment(), TAG_ALBUM_FRAGMENT)
            .commit()
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right)
            .add(R.id.fragmentContainer, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    private fun updateToolbar() {

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        when (currentFragment?.tag) {
            TAG_ALBUM_FRAGMENT -> setToolbarItems(false, "Albums")
            TAG_PHOTO_FRAGMENT -> setToolbarItems(true, "Photos")
            TAG_DETAIL_FRAGMENT -> setToolbarItems(true, "More Info")
        }
    }

    private fun setToolbarItems(backButtonEnabled: Boolean, title: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(backButtonEnabled)
        supportActionBar?.setTitle(title)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun navigateToPhotoList(photoList: ArrayList<Photo>) {
        val fragment = PhotoListFragment.newInstance(photoList)
        addFragment(fragment, TAG_PHOTO_FRAGMENT)
    }

    override fun navigateToPhotoDetail(title: String, imageUrl: String) {
        val fragment = PhotoDetailFragment.newInstance(title, imageUrl)
        addFragment(fragment, TAG_DETAIL_FRAGMENT)
    }
}
