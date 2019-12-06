package by.wolearn.settings.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.wolearn.R
import by.wolearn.core.utils.mainNavController
import by.wolearn.core.utils.showError
import by.wolearn.core.utils.showProgress
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.ResourceObserver
import by.wolearn.settings.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class SettingsFragment : Fragment(R.layout.fragment_settings) {
    val prefs: SharedPreferences by inject()
    val model: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { ctx: Context ->
            val arrAdapter = ArrayAdapter.createFromResource(
                ctx,
                R.array.themes,
                android.R.layout.simple_spinner_item
            )
            arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            with(appearance) {
                adapter = arrAdapter
                val select =
                    arrAdapter.getPosition(prefs.getString(getString(R.string.key_appearance), ""))
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val newTheme = appearance.selectedItem.toString()
                        if (prefs.getString(getString(R.string.key_appearance), null) != newTheme) {
                            prefs.edit {
                                putString(getString(R.string.key_appearance), newTheme)
                            }
                        }
                    }
                }
                setSelection(select, false)
            }

        }
        resetStatistics.setOnClickListener { model.resetStatistics() }
        logout.setOnClickListener { model.logout() }

        model.logout.observe(
            viewLifecycleOwner,
            Observer { mainNavController.navigate(R.id.action_global_loginFragment) })
        model.statReset.observe(viewLifecycleOwner, object : ResourceObserver<Unit>() {
            override fun onError(error: Resource.Error<Unit>) = showError(error)
            override fun onLoad() = showProgress(true)
            override fun onSuccess(data: Unit?) {
                showProgress(false)
            }
        })
    }
}