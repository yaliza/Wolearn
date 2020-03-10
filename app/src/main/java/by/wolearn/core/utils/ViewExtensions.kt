package by.wolearn.core.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun ImageView.load(id: Int) {
    Glide.with(this).load(id).into(this)
}

fun ImageView.load(url: String) {
    Glide.with(this).load(url).into(this)
}

fun ImageView.loadCircle(url: String) {
    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(this)
}