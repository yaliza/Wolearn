package by.wolearn.profile.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
import by.wolearn.core.utils.showError
import by.wolearn.core.utils.showProgress
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.ResourceObserver
import by.wolearn.profile.model.HistoryWord
import by.wolearn.profile.model.Profile
import by.wolearn.profile.model.ProfileInfo
import by.wolearn.profile.model.Statistics
import by.wolearn.profile.viewmodel.ProfileViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.view_profile_info.*
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    val model: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HistoryAdapter()
        history.adapter = adapter
        history.layoutManager = LinearLayoutManager(activity)

        Glide.with(avatar)
            .load(R.drawable.ic_avatar)
            .apply(RequestOptions().circleCrop())
            .into(avatar)

        model.profile.observe(viewLifecycleOwner, object : ResourceObserver<Profile>() {
            override fun onError(error: Resource.Error<Profile>) = showError(error)
            override fun onLoad() = showProgress(true)
            override fun onSuccess(data: Profile?) {
                data?.statistics?.let { stat: Statistics ->
                    totalWords.text = stat.total.toString()
                    todayWords.text = stat.today.toString()
                    totalCategories.text = stat.categories.toString()
                }
                data?.info?.let { info: ProfileInfo ->
                    name.text = getString(R.string.profile_name, info.name, info.surname)
                }
            }
        })

        model.history.observe(viewLifecycleOwner, object : ResourceObserver<List<HistoryWord>>() {
            override fun onError(error: Resource.Error<List<HistoryWord>>) = showError(error)
            override fun onLoad() {}
            override fun onSuccess(data: List<HistoryWord>?) {
                data?.let { adapter.items = data }
            }
        })
    }

}