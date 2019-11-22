package by.wolearn.learning.model.repositories

import by.wolearn.network.WolearnApi


class LearningRepository(
    private val api: WolearnApi
) {

    suspend fun getWords() = api.getWords()
}