package by.wolearn.profile.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.wolearn.R
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

        totalWords.text = "2"
        todayWords.text = "1"
        totalCategories.text = "5"
    }

}