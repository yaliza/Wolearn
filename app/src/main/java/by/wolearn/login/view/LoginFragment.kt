package by.wolearn.login.view

import androidx.fragment.app.Fragment
import by.wolearn.R
import by.wolearn.login.viewmodel.LoginViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.fragment_login) {

    val model : LoginViewModel by viewModel()

}