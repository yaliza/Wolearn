package by.wolearn.learning.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import kotlinx.android.synthetic.main.fragment_learning.*

class LearningFragment : Fragment(R.layout.fragment_learning) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CategoriesAdapter()
        categories.adapter = adapter
        categories.layoutManager = LinearLayoutManager(activity)
    }

}