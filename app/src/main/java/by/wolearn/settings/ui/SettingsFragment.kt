package by.wolearn.settings.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.core.Snackbar
import by.wolearn.core.utils.mainNavController
import by.wolearn.settings.ui.adapter.SettingsController
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.view_progress_transparent.*
import org.koin.android.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment(R.layout.fragment_settings), SettingsController.Listener {

    val model: SettingsViewModel by viewModel()

    private lateinit var settingsController: SettingsController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupViewModel()
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
        model.showLearningMode.observe(viewLifecycleOwner, Observer {
            if (it.content == null) return@Observer
            mainNavController.navigate(R.id.action_bottomNavigationFragment_to_learningModeFragment)
        })
        model.snackBarInfo.observe(viewLifecycleOwner, Observer {
            val str = it.content ?: return@Observer
            progress.visibility = View.GONE
            Snackbar.make(view, getString(str), Snackbar.LENGTH_LONG)
        })
    }

    private fun showState(state: SettingsViewModel.State) {
        when (state) {
            SettingsViewModel.State.Logout -> mainNavController.navigate(R.id.action_global_loginFragment)
            SettingsViewModel.State.Progress -> {
                progress.visibility = View.VISIBLE
                view?.findViewById<Button>(R.id.error)?.visibility = View.GONE
            }
            is SettingsViewModel.State.Success -> {
                progress.visibility = View.GONE
                view?.findViewById<Button>(R.id.error)?.visibility = View.GONE
                settingsController.items = state.items
            }
        }
    }

    override fun onButtonClick(id: String) = model.onButtonClick(id)

    private fun setupRecycler() {
        settingsController = SettingsController(this)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = settingsController.adapter
    }
}