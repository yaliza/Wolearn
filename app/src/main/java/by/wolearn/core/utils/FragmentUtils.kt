package by.wolearn.core.utils

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.wolearn.R
import by.wolearn.core.view.entities.Resource
import com.google.android.material.snackbar.Snackbar


fun Fragment.showMessage(msg: String) {
    view?.let { Snackbar.make(it, msg, Snackbar.LENGTH_LONG).show() }
}

fun <T> Fragment.showError(e: Resource.Error<T>) {
    showProgress(false)
    when (e) {
        is Resource.Error.HttpError -> showMessage(
            getString(R.string.error_http, e.code, e.message)
        )
        is Resource.Error.NetworkError -> showMessage(getString(R.string.error_network))
        is Resource.Error.UnknownError -> showMessage(getString(R.string.error_unknown))
    }
}

fun Fragment.showProgress(show: Boolean) {
    val currentDest = mainNavController.currentDestination?.id
    if (show) {
        if (currentDest != R.id.progressDialogFragment) mainNavController.navigate(R.id.action_global_ProgressDialogFragment)
    } else {
        if (currentDest == R.id.progressDialogFragment) mainNavController.popBackStack()
    }
}

val Fragment.bottomNavController
    get() = requireActivity().findNavController(R.id.bottomNavHostFragment)

val Fragment.mainNavController
    get() = requireActivity().findNavController(R.id.mainNavHostFragment)