package com.sami.linxogalleryapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sami.linxogalleryapp.R
import com.sami.linxogalleryapp.presentation.album_list.AlbumListFragment
import com.sami.linxogalleryapp.util.replaceFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_toolbar_layout.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        replaceFragment(fragmentManager = supportFragmentManager, AlbumListFragment(), false)

        imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}