package by.wolearn.learning.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
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
        categories.adapter = adapter
        categories.layoutManager = LinearLayoutManager(activity)
        learnWords.setOnClickListener { mainNavController.navigate(R.id.action_bottomNavigationFragment_to_learningFragment) }
        repeatWords.setOnClickListener { mainNavController.navigate(R.id.action_bottomNavigationFragment_to_learningFragment) }

        model.categories.observe(viewLifecycleOwner, Observer { adapter.items = it })
    }

}