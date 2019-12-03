package by.wolearn.core.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import by.wolearn.R
import by.wolearn.core.utils.bottomNavController
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*


class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomMenu.setupWithNavController(bottomNavController)
    }

}