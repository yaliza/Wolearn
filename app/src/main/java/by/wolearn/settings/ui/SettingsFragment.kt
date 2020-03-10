package by.wolearn.settings.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.core.utils.mainNavController
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.view_progress_transparent.*
import org.koin.android.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    val model: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
    }

    private fun showState(state: SettingsViewModel.State) {
        when (state) {
            SettingsViewModel.State.Logout -> mainNavController.navigate(R.id.action_global_loginFragment)
            SettingsViewModel.State.Progress -> {
                progress.visibility = View.VISIBLE
                content.visibility = View.VISIBLE
            }
        }
    }

    private fun setupView() {
        logout.setOnClickListener { model.logout() }
        deleteAccount.setOnClickListener { model.deleteProfile() }
    }
}