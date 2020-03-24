package by.wolearn.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.wolearn.R
import by.wolearn.core.extensions.mainNavController


class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainNavController.navigate(R.id.action_splashFragment_to_loginFragment)
    }
}