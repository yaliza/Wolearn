package by.wolearn.categories.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.categories.data.Category
import by.wolearn.core.utils.showError
import by.wolearn.core.utils.showProgress
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.ResourceObserver
import by.wolearn.core.view.fragments.BottomNavigationFragmentDirections
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.viewmodel.ext.android.viewModel


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val mainNavController by lazy { requireActivity().findNavController(R.id.mainNavHostFragment) }
    private val model: CategoriesViewModel by viewModel()
    private lateinit var categoriesAdapter : CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.onCategorySelected = { category, b -> model.update(category, b) }
        categories.adapter = categoriesAdapter
        categories.layoutManager = LinearLayoutManager(activity)

        learnWords.setOnClickListener { goToLearning(false) }
        repeatWords.setOnClickListener { goToLearning(true) }

        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
    }

    private fun showState(state: CategoriesViewModel.State) {
        when(state) {
            is CategoriesViewModel.State.Success -> categoriesAdapter.items = state.categories
        }
    }

    private fun goToLearning(isRepeating: Boolean) {
        val direction =
            BottomNavigationFragmentDirections.actionBottomNavigationFragmentToLearningFragment(
                isRepeating
            )
        mainNavController.navigate(direction)
    }

}