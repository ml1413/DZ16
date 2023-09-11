package com.example.dz16.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.dz16.Details
import com.example.dz16.R


fun ImageView.setImage(url: String, circleCrop: Boolean, view: ImageView? = null) {
    Glide.with(this.context)
        .load(url).apply {
            view?.let { into(it) }
            if (circleCrop) this@apply.circleCrop()
            into(this@setImage)
        }

}
