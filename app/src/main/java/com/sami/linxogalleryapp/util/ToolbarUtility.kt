package com.sami.linxogalleryapp.util

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.custom_toolbar_layout.*
import java.util.*

/**
 * configure Custom Toolbar with title and back button
 */

fun configureHomeToolBar(
    activity: AppCompatActivity,
    toolbarTitle: String,
    isBackButtonVisible: Boolean
) {
    if (isBackButtonVisible){
        activity.imgBack.visibility = View.VISIBLE
    }else{
        activity.imgBack.visibility = View.GONE
    }

    activity.txtToolbarTitle.text = toolbarTitle.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}