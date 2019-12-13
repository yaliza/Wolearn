package by.wolearn.learning.model.repositories

import by.wolearn.learning.model.entities.MemorizeWord
import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall


class LearningRepository(private val api: WolearnApi) {

    suspend fun getWords() = safeApiCall { api.getWords() }
    suspend fun getRepeatWords() = safeApiCall { api.getRepeatWords() }
    suspend fun saveWord(item: MemorizeWord) = safeApiCall { api.saveWord(item) }
}