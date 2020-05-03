package by.wolearn.learningmode.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.core.utils.mainNavController
import by.wolearn.learningmode.ui.adapter.LearningModeController
import kotlinx.android.synthetic.main.fragment_learning_mode.*
import kotlinx.android.synthetic.main.view_progress_transparent.*
import org.koin.android.viewmodel.ext.android.viewModel

class LearningModeFragment : Fragment(R.layout.fragment_learning_mode),
    LearningModeController.Listener {

    val model: LearningModeViewModel by viewModel()

    private lateinit var learningModeController: LearningModeController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecycler()
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
        model.back.observe(viewLifecycleOwner, Observer { mainNavController.popBackStack() })
    }

    private fun setupRecycler() {
        learningModeController = LearningModeController(this)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = learningModeController.adapter
    }

    private fun showState(state: LearningModeViewModel.State) {
        when (state) {
            is LearningModeViewModel.State.Data -> {
                progress.visibility = View.GONE
                learningModeController.items = state.items
            }
            LearningModeViewModel.State.Progress -> {
                progress.visibility = View.VISIBLE
            }
        }
    }

    override fun onOptionSelected(id: String) = model.onOptionSelected(id)
}