package by.wolearn.splash

import android.os.Bundle
import android.view.View
import androidx.core.view.postDelayed
import androidx.fragment.app.Fragment
import by.wolearn.R
import by.wolearn.auth.AuthPreferences
import by.wolearn.core.utils.mainNavController


class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!AuthPreferences.isSignIn()) {
            view.postDelayed(1000) {
                mainNavController.navigate(R.id.action_splashFragment_to_loginFragment)
            }
        } else {
            view.postDelayed(1000) {
                mainNavController.navigate(R.id.action_splashFragment_to_bottomNavigationFragment)
            }
        }
    }
}