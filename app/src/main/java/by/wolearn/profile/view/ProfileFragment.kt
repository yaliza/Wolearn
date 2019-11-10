package by.wolearn.profile.view

import androidx.fragment.app.Fragment
import by.wolearn.R
import by.wolearn.profile.viewmodel.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    val model: ProfileViewModel by viewModel()
}