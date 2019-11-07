package by.wolearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import by.wolearn.core.utils.hide
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        findNavController(R.id.navHostFragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.loginFragmentDestination -> toolbar.hide()
            }
        }
    }

}
