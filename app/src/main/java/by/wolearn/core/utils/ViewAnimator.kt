package by.wolearn.core.utils

import android.view.View
import android.widget.ViewAnimator


fun ViewAnimator.show(id: Int) {
    if (this.currentView.id == id) return
    if (findViewById<View>(id) == null) return
    while (currentView.id != id) showNext()
}