package by.wolearn.learningmode.data

import by.wolearn.core.data.AppPreferences
import java.lang.Exception

class LearningModeRepository(private val appPreferences: AppPreferences) {

    fun getLearningMode(): LearningMode {
        return appPreferences.getLearningMode()
    }

    fun saveLearningMode(mode: LearningMode) {
        appPreferences.putLearningMode(mode)
    }

}