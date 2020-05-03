package by.wolearn.core.data

import android.content.Context
import androidx.core.content.edit
import by.wolearn.learningmode.data.LearningMode
import java.lang.Exception

class AppPreferences(val context: Context) {

    private val preferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    fun getLearningMode(): LearningMode {
        return try {
            LearningMode.valueOf(preferences.getString(KEY_LEARNING_MODE, "") ?: "")
        } catch (ex: Exception) {
            return LearningMode.MIXED
        }
    }

    fun putLearningMode(mode: LearningMode) {
        preferences.edit {
            putString(KEY_LEARNING_MODE, mode.toString())
        }
    }

    companion object {
        const val KEY_LEARNING_MODE = "LEARNING_MODE"
    }
}