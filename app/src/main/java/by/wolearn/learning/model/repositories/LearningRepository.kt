package by.wolearn.learning.model.repositories

import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall


class LearningRepository(private val api: WolearnApi) {

    suspend fun getWords() = safeApiCall { api.getWords() }

}