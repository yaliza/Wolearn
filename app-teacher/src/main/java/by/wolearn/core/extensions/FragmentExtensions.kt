package by.wolearn.core.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.wolearn.R

val Fragment.bottomNavController
    get() = requireActivity().findNavController(R.id.bottomNavHostFragment)

val Fragment.mainNavController
    get() = requireActivity().findNavController(R.id.mainNavHostFragment)