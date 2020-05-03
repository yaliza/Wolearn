package by.wolearn.chooser.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.chooser.ui.adapter.CategoriesController
import by.wolearn.chooser.ui.entities.CategoryItem
import by.wolearn.core.extensions.mainNavController
import by.wolearn.core.ui.show
import kotlinx.android.synthetic.main.fragment_category_chooser.*
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryChooserFragment : Fragment(R.layout.fragment_category_chooser),
    CategoriesController.Listener {

    private val model: CategoryChooserViewModel by viewModel()
    private lateinit var categoriesController: CategoriesController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer {
            showState(it)
        })
    }

    private fun setupRecyclerView() {
        categoriesController = CategoriesController(this)
        categories.layoutManager = LinearLayoutManager(context)
        categories.adapter = categoriesController.adapter
    }

    override fun onCategoryChoosed(categoryItem: CategoryItem) {
        model.onCategoryChoosed(categoryItem)
    }

    private fun showState(state: CategoryChooserViewModel.State) {
        when (state) {
            is CategoryChooserViewModel.State.Data -> {
                animator.show(R.id.categories)
                categoriesController.items = state.items
            }
            is CategoryChooserViewModel.State.Success -> {
                mainNavController.previousBackStackEntry?.savedStateHandle?.set(KEY_RESULT, state.item.category)
                mainNavController.popBackStack()
            }
            CategoryChooserViewModel.State.Error -> animator.show(R.id.error)
            CategoryChooserViewModel.State.Progress -> animator.show(R.id.progress)
        }
    }

    companion object {
        const val KEY_RESULT = "CATEGORY"
    }
}