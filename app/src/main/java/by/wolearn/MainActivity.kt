package by.wolearn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.wolearn.core.utils.hide
import by.wolearn.core.utils.show
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        findNavController(R.id.navHostFragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registrationFragment -> {
                    bottomMenu.hide()
                    toolbar.hide()
                }
                else -> {
                    actionBar?.title = "hello"
                    bottomMenu.show()
                    toolbar.show()
                }
            }
        }
        bottomMenu.setupWithNavController(findNavController(R.id.navHostFragment))
    }

}
