package by.wolearn.core.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.wolearn.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*


class BottomNavigationFragment : Fragment(R.layout.fragment_bottom_navigation) {

    private val bottomNavController by lazy { requireActivity().findNavController(R.id.bottomNavHostFragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomMenu.setupWithNavController(bottomNavController)
    }

}