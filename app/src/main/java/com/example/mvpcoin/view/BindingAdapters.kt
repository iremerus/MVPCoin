package com.example.mvpcoin.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("url")
fun setImage(imageView: ImageView, url: String) {
    Picasso.with(imageView.context).load(url).into(imageView)
}

