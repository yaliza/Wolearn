package by.wolearn.learning.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.learning.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.viewmodel.ext.android.viewModel

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    val model: CategoriesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CategoriesAdapter()
        categories.adapter = adapter
        categories.layoutManager = LinearLayoutManager(activity)
        learnWords.setOnClickListener { findNavController().navigate(R.id.action_bottomNav_to_WordFragment) }
        repeatWords.setOnClickListener { findNavController().navigate(R.id.action_bottomNav_to_WordFragment) }
    }

}