package by.wolearn.login.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.core.extensions.mainNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    val model: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
    }

    private fun setupView() {
        loginAction.setOnClickListener {
            model.login(login.text.toString(), password.text.toString())
        }
    }

    private fun showState(state: LoginViewModel.State) {
        when (state) {
            LoginViewModel.State.Success -> {
                mainNavController.navigate(R.id.action_loginFragment_to_bottomNavigationFragment)
            }
            LoginViewModel.State.Progress -> {

            }
            is LoginViewModel.State.Error ->
                Snackbar.make(view!!, state.message, Snackbar.LENGTH_LONG).show()
            is LoginViewModel.State.LoginError -> {
                login.error = state.message
            }
            is LoginViewModel.State.PasswordError -> {
                password.error = state.message
            }
            LoginViewModel.State.UnknownError -> {

            }
        }
    }

}