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

fun ImageView.load(id: Int) {
    Glide.with(this).load(id).into(this)
}