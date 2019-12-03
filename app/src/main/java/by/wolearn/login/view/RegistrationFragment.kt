package by.wolearn.login.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.wolearn.R
import by.wolearn.core.utils.mainNavController
import by.wolearn.login.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.android.viewmodel.ext.android.viewModel


class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    val model: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register.setOnClickListener { mainNavController.navigate(R.id.action_registrationFragment_to_bottomNavigationFragment) }
    }

}