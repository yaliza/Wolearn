package by.wolearn.login.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.wolearn.R
import by.wolearn.core.utils.mainNavController
import by.wolearn.core.utils.showError
import by.wolearn.core.utils.showProgress
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.ResourceObserver
import by.wolearn.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.fragment_login) {

    val model: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register.setOnClickListener { mainNavController.navigate(R.id.action_loginFragment_to_registrationFragment) }
        login.setOnClickListener {
            model.login(textLogin.text.toString(), textPassword.text.toString())
        }

        model.loginResult.observe(viewLifecycleOwner, object : ResourceObserver<Unit>() {
            override fun onError(error: Resource.Error<Unit>) = showError(error)
            override fun onLoad() = showProgress(true)
            override fun onSuccess(data: Unit?) {
                showProgress(false)
                mainNavController.navigate(R.id.action_loginFragment_to_bottomNavigationFragment)
            }
        })
    }
}