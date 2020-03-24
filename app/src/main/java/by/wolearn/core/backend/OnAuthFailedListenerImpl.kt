package by.wolearn.core.backend

import android.content.Context
import android.content.Intent
import by.wolearn.MainActivity
import by.wolearn.auth.OnAuthFailedListener


class OnAuthFailedListenerImpl(private val context: Context) : OnAuthFailedListener {

    override fun onAuthFailed() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}

