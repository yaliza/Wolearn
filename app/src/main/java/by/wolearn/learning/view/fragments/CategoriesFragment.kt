package by.wolearn.learning.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.core.utils.showError
import by.wolearn.core.utils.showProgress
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.ResourceObserver
import by.wolearn.learning.model.entities.Category
import by.wolearn.learning.view.adapters.CategoriesAdapter
import by.wolearn.learning.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.viewmodel.ext.android.viewModel


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val mainNavController by lazy { requireActivity().findNavController(R.id.mainNavHostFragment) }
    val model: CategoriesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoriesAdapter()
        adapter.onCategorySelected = { category, b -> model.update(category, b) }
        categories.adapter = adapter
        categories.layoutManager = LinearLayoutManager(activity)

        learnWords.setOnClickListener { mainNavController.navigate(R.id.action_bottomNavigationFragment_to_learningFragment) }
        repeatWords.setOnClickListener { mainNavController.navigate(R.id.action_bottomNavigationFragment_to_learningFragment) }

        model.categories.observe(viewLifecycleOwner, object : ResourceObserver<List<Category>>() {
            override fun onError(error: Resource.Error<List<Category>>) = showError(error)
            override fun onLoad() = showProgress(true)
            override fun onSuccess(data: List<Category>?) {
                showProgress(false)
                data?.let { adapter.items = it }
            }
        })
    }

}