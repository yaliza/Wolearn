package by.wolearn.login.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.core.Snackbar
import by.wolearn.core.utils.mainNavController
import kotlinx.android.synthetic.main.fragment_login_content.*
import kotlinx.android.synthetic.main.view_progress_transparent.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.fragment_login) {

    val model: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupView()
    }

    private fun setupView() {
        loginButton.setOnClickListener {
            model.login(login.text.toString(), password.text.toString())
        }
        register.setOnClickListener {
            mainNavController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        view?.findViewById<Button>(R.id.retry)?.setOnClickListener {
            model.login(login.text.toString(), password.text.toString())
        }
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
        model.clearErrors.observe(viewLifecycleOwner, Observer {
            loginInput.error = null
            passwordInput.error = null
        })
    }

    private fun showState(state: LoginViewModel.State) {
        when (state) {
            LoginViewModel.State.Success -> mainNavController.navigate(R.id.action_loginFragment_to_bottomNavigationFragment)
            LoginViewModel.State.Progress -> {
                progress.visibility = View.VISIBLE
                content.visibility = View.VISIBLE
                view?.findViewById<Button>(R.id.error)?.visibility = View.GONE
            }
            LoginViewModel.State.UnknownError -> {
                progress.visibility = View.GONE
                content.visibility = View.GONE
                view?.findViewById<Button>(R.id.error)?.visibility = View.VISIBLE
            }
            is LoginViewModel.State.LoginError -> {
                showContent()
                loginInput.error = state.message
            }
            is LoginViewModel.State.PasswordError -> {
                showContent()
                passwordInput.error = state.message
            }
            is LoginViewModel.State.Error -> {
                showContent()
                Snackbar.make(view, state.message, Snackbar.LENGTH_LONG)?.show()
            }
        }
    }

    private fun showContent() {
        progress.visibility = View.GONE
        content.visibility = View.VISIBLE
        view?.findViewById<Button>(R.id.error)?.visibility = View.GONE
    }

}