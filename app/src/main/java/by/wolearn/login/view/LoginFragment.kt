package by.wolearn.login.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.wolearn.R
import by.wolearn.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val mainNavController by lazy { findNavController() }
    val model: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registrationFragment) }
        login.setOnClickListener { mainNavController.navigate(R.id.action_loginFragment_to_bottomNavigationFragment) }
    }
}