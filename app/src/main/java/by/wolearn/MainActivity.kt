package by.wolearn

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.wolearn.core.utils.hide
import by.wolearn.core.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val prefs: SharedPreferences by inject()
    private val prefsListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == getString(R.string.key_appearance)) {
                recreate()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        findNavController(R.id.navHostFragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registrationFragment, R.id.learningFragment -> {
                    bottomMenu.hide()
                    toolbar.hide()
                }
                else -> {
                    bottomMenu.show()
                    toolbar.show()
                }
            }
        }
        bottomMenu.setupWithNavController(findNavController(R.id.navHostFragment))
    }

    override fun onResume() {
        super.onResume()
        prefs.registerOnSharedPreferenceChangeListener(prefsListener)
    }

    override fun onPause() {
        super.onPause()
        prefs.unregisterOnSharedPreferenceChangeListener(prefsListener)
    }

    private fun setupTheme() {
        var currentTheme = prefs.getString(getString(R.string.key_appearance), null)
        val themeArray = resources.getStringArray(R.array.themes)
        if (currentTheme == null) {
            currentTheme = themeArray[0]
            prefs.edit { putString(getString(R.string.key_appearance), currentTheme) }
        }
        when (themeArray.indexOf(currentTheme)) {
            0 -> setTheme(R.style.VioletTheme)
            1 -> setTheme(R.style.YellowTheme)
        }
    }
}