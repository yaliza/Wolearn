package by.wolearn.mywords.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.core.extensions.mainNavController
import by.wolearn.core.ui.show
import kotlinx.android.synthetic.main.fragment_my_words.*
import org.koin.android.viewmodel.ext.android.viewModel


class MyWordsFragment : Fragment(R.layout.fragment_my_words) {

    val model: MyWordsViewModel by viewModel()

    private lateinit var myWordsAdapter: MyWordsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecycler()
        setupActions()
    }

    private fun setupRecycler() {
        myWordsAdapter = MyWordsAdapter()
        mywords.adapter = myWordsAdapter
        mywords.layoutManager = LinearLayoutManager(context)
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
    }

    private fun setupActions() {
        view?.findViewById<Button>(R.id.retry)?.setOnClickListener { model.loadWords() }
        swipe.setOnRefreshListener { model.loadWords() }
        addWord.setOnClickListener { mainNavController.navigate(R.id.action_bottomNavigationFragment_to_addWordFragment) }
    }

    private fun showState(state: MyWordsViewModel.State) {
        when (state) {
            is MyWordsViewModel.State.Data -> {
                animator.show(R.id.content)
                swipe.isRefreshing = false
                myWordsAdapter.items = state.items
            }
            MyWordsViewModel.State.Progress -> if (!swipe.isRefreshing) animator.show(R.id.progress)
            MyWordsViewModel.State.Error -> animator.show(R.id.error)
        }
    }
}
