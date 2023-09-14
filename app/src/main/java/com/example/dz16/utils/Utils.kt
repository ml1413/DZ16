package com.example.dz16.utils

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.dz16.MainActivity
@SuppressLint("StaticFieldLeak")
lateinit var progressBar: ProgressBar

fun ImageView.setImage(url: String, circleCrop: Boolean, view: ImageView? = null) {
    Glide.with(this.context)
        .load(url).apply {
            view?.let { into(it) }
            if (circleCrop) this@apply.circleCrop()
            into(this@setImage)
        }

}

fun onOffProgressBar() {
    Log.d("TAG1", "onOffProgressBar: ")
    progressBar.isVisible = !progressBar.isVisible
}