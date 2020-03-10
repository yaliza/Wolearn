package by.wolearn.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


object AuthPreferences {

    private const val KEY_JWT_TOKEN = "jwt_token"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    }

    fun safeJwt(jwt: String) {
        prefs.edit { putString(KEY_JWT_TOKEN, jwt) }
    }

    fun getJwt(): String {
        return prefs.getString(KEY_JWT_TOKEN, "") ?: ""
    }

    fun isSignIn(): Boolean {
        return prefs.getString(KEY_JWT_TOKEN, null) != null
    }

    fun clear() {
        prefs.edit {
            clear()
        }
    }

}