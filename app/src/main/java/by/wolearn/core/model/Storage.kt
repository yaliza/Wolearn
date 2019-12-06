package by.wolearn.core.model

import android.content.SharedPreferences
import androidx.core.content.edit


interface Storage<T> {
    fun get(): T?
    fun store(storeItem: T)
    fun delete()
}

abstract class PrefsStorage<T>(val prefs: SharedPreferences) : Storage<T>

class TokenStorage(prefs: SharedPreferences) : PrefsStorage<Token>(prefs) {

    override fun get(): Token? {
        val str = prefs.getString(STORAGE_TOKEN, null)
        return if (str != null) Token(str) else null
    }

    override fun store(storeItem: Token) {
        prefs.edit { putString(STORAGE_TOKEN, storeItem.token) }
    }

    override fun delete() {
        prefs.edit { remove(STORAGE_TOKEN) }
    }

}