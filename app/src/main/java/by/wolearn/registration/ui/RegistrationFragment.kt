package by.wolearn.registration.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.core.Snackbar
import by.wolearn.core.utils.mainNavController
import kotlinx.android.synthetic.main.fragment_registration_content.*
import kotlinx.android.synthetic.main.view_progress_transparent.*
import org.koin.android.viewmodel.ext.android.viewModel


class RegistrationFragment() : Fragment(R.layout.fragment_registration) {

    val model: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
        model.clearErrors.observe(viewLifecycleOwner, Observer {
            loginInput.error = null
            passwordInput.error = null
            nameInput.error = null
            surnameInput.error = null
        })
    }

    private fun setupView() {
        view?.findViewById<Button>(R.id.retry)?.setOnClickListener { register() }
        registrationButton.setOnClickListener { register() }
    }

    private fun register() {
        model.register(
            name.text.toString(),
            surname.text.toString(),
            login.text.toString(),
            password.text.toString()
        )
    }

    private fun showState(state: RegistrationViewModel.State) {
        when (state) {
            RegistrationViewModel.State.Success -> mainNavController.navigate(R.id.action_registrationFragment_to_bottomNavigationFragment)
            RegistrationViewModel.State.Progress -> {
                progress.visibility = View.VISIBLE
                content.visibility = View.VISIBLE
                view?.findViewById<Button>(R.id.error)?.visibility = View.GONE
            }
            RegistrationViewModel.State.UnknownError -> {
                progress.visibility = View.GONE
                content.visibility = View.GONE
                view?.findViewById<Button>(R.id.error)?.visibility = View.VISIBLE
            }
            is RegistrationViewModel.State.Error -> {
                progress.visibility = View.GONE
                content.visibility = View.VISIBLE
                view?.findViewById<Button>(R.id.error)?.visibility = View.GONE
                showError(state)
            }
        }
    }

    private fun showError(state: RegistrationViewModel.State.Error) {
        when (state) {
            is RegistrationViewModel.State.Error.NameError -> nameInput.error = state.message
            is RegistrationViewModel.State.Error.SurnameError -> surnameInput.error = state.message
            is RegistrationViewModel.State.Error.LoginError -> loginInput.error = state.message
            is RegistrationViewModel.State.Error.PasswordError ->
                passwordInput.error = state.message
            is RegistrationViewModel.State.Error.CommonError ->
                Snackbar.make(view, state.message, Snackbar.LENGTH_LONG)?.show()
        }
    }

}