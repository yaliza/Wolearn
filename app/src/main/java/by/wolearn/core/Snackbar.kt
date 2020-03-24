package by.wolearn.core

import android.view.View
import com.google.android.material.snackbar.Snackbar


object Snackbar {

    const val LENGTH_LONG = Snackbar.LENGTH_LONG
    const val LENGTH_SHORT = Snackbar.LENGTH_SHORT

    fun make(view: View?, message: String, duration: Int): Snackbar? {
        if (view == null) return null
        return Snackbar.make(view, message, duration)
    }

}