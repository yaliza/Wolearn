package by.wolearn.profile.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.core.utils.Snackbar
import by.wolearn.core.utils.show
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.view_profile_info.*
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val model: ProfileViewModel by viewModel()

    private lateinit var historyAdapter: HistoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupViewModel()
        setupView()
    }

    private fun setupView() {
        Glide.with(avatar)
            .load(R.drawable.ic_avatar)
            .apply(RequestOptions().circleCrop())
            .into(avatar)
        content.setOnRefreshListener { model.refresh() }
    }

    private fun setupViewModel() {
        model.state.observe(viewLifecycleOwner, Observer { showState(it) })
    }

    private fun showState(state: ProfileViewModel.State) {
        when (state) {
            is ProfileViewModel.State.Data -> {
                content.isRefreshing = false
                animator.show(R.id.content)
                with(state.profile) {
                    totalNumber.number = statistics.total
                    todayNumber.number = statistics.today
                    categoriesNumber.number = statistics.categories
                    this@ProfileFragment.name.text = name
                }
                historyAdapter.items = state.history
            }
            is ProfileViewModel.State.Error -> {
                content.isRefreshing = false
                animator.show(R.id.content)
                Snackbar.make(view, state.message, Snackbar.LENGTH_LONG)
            }
            ProfileViewModel.State.Progress -> animator.show(R.id.progress)
            ProfileViewModel.State.UnknownError -> animator.show(R.id.error)
        }
    }

    private fun setupRecycler() {
        historyAdapter = HistoryAdapter()
        history.adapter = historyAdapter
        history.layoutManager = LinearLayoutManager(activity)
    }

}