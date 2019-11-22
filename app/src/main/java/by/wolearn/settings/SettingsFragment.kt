package by.wolearn.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.wolearn.R
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.ext.android.inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val mainNavController by lazy { requireActivity().findNavController(R.id.mainNavHostFragment) }
    val prefs: SharedPreferences by inject()

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
                        prefs.edit {
                            putString(getString(R.string.key_appearance), newTheme)
                        }
                    }
                }
                setSelection(select, false)
            }

        }
        logout.setOnClickListener { mainNavController.navigate(R.id.action_global_loginFragment) }
    }
}