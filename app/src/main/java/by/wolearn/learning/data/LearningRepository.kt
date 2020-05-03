package by.wolearn.learning.data

import by.wolearn.core.data.AppPreferences
import by.wolearn.learning.backend.WordsApi
import by.wolearn.core.safeApiCall
import by.wolearn.learningmode.data.LearningMode


class LearningRepository(
    private val api: WordsApi,
    private val appPreferences: AppPreferences
) {

    suspend fun getWords(offset: Int = 0, size: Int = 6) = safeApiCall {
        when (appPreferences.getLearningMode()) {
            LearningMode.REPEAT -> api.getWords(offset, size, isRepeat = true, isNew = false)
            LearningMode.NEW -> api.getWords(offset, size, isRepeat = false, isNew = true)
            LearningMode.MIXED -> api.getWords(offset, size, isRepeat = true, isNew = true)
        }
    }

    suspend fun saveWord(wordId: Long, isMemorized: Boolean) =
        safeApiCall { api.saveWord(wordId, isMemorized) }

}