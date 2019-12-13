package by.wolearn.login.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.core.utils.mainNavController
import by.wolearn.core.utils.showError
import by.wolearn.core.utils.showMessage
import by.wolearn.core.utils.showProgress
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.ResourceObserver
import by.wolearn.login.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.android.viewmodel.ext.android.viewModel


class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    val model: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.validation.observe(viewLifecycleOwner, Observer { showMessage(it) })
        model.registration.observe(viewLifecycleOwner, object : ResourceObserver<Unit>() {
            override fun onError(error: Resource.Error<Unit>) = showError(error)
            override fun onLoad() = showProgress(true)
            override fun onSuccess(data: Unit?) {
                showProgress(false)
                mainNavController.navigate(R.id.action_registrationFragment_to_bottomNavigationFragment)
            }
        })
        register.setOnClickListener {
            model.register(
                login.text.toString(),
                password.text.toString(),
                name.text.toString(),
                surname.text.toString()
            )
        }
    }

}