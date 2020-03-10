package by.wolearn.categories.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.core.utils.Snackbar
import by.wolearn.core.utils.show
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.viewmodel.ext.android.viewModel


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val model: CategoriesViewModel by viewModel()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecycler()
        setupView()
    }

    private fun setupView() {
        retry.setOnClickListener { model.refresh() }
        content.setOnRefreshListener { model.refresh() }
    }

    private fun setupRecycler() {
        categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.onCategorySelected = { category, b -> model.update(category, b) }
        categoriesIcon.adapter = categoriesAdapter
        categoriesIcon.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
    }

    private fun showState(state: CategoriesViewModel.State) {
        when (state) {
            is CategoriesViewModel.State.Success -> {
                animator.show(R.id.content)
                content.isRefreshing = false
                categoriesAdapter.items = state.categories
            }
            is CategoriesViewModel.State.Error -> {
                animator.show(R.id.content)
                content.isRefreshing = false
                Snackbar.make(view, state.message, Snackbar.LENGTH_LONG)?.show()
            }
            CategoriesViewModel.State.Progress -> if (!content.isRefreshing) animator.show(R.id.progress)
            CategoriesViewModel.State.UnknownError -> animator.show(R.id.error)
        }
    }

}