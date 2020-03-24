package by.wolearn.learning.data

import by.wolearn.learning.backend.WordsApi
import by.wolearn.core.safeApiCall


class LearningRepository(private val api: WordsApi) {

    suspend fun getWords() = by.wolearn.core.safeApiCall { api.getWords() }

    suspend fun saveWord(wordId: Long, isMemorized: Boolean) =
        by.wolearn.core.safeApiCall { api.saveWord(wordId, isMemorized) }

}