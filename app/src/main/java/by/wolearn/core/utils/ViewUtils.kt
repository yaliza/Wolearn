package by.wolearn.core.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

const val IMAGE_BASE_URL = "https://wolearn-api.herokuapp.com/"

fun ImageView.load(id: Int) {
    Glide.with(this).load(id).into(this)
}

fun ImageView.load(url: String) {
    Glide.with(this).load("$IMAGE_BASE_URL$url").into(this)
}