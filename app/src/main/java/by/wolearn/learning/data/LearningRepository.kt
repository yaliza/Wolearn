package by.wolearn.learning.data

import by.wolearn.learning.backend.WordsApi
import by.wolearn.core.utils.safeApiCall


class LearningRepository(private val api: WordsApi) {

    suspend fun getWords() = safeApiCall { api.getWords() }

    suspend fun saveWord(wordId: Long, isMemorized: Boolean) =
        safeApiCall { api.saveWord(wordId, isMemorized) }

}