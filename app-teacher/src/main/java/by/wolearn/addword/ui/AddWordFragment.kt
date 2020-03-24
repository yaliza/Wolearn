package by.wolearn.addword.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.wolearn.R
import org.koin.android.viewmodel.ext.android.viewModel


class AddWordFragment : Fragment(R.layout.fragment_add_word) {

    val model: AddWordViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {

    }
}