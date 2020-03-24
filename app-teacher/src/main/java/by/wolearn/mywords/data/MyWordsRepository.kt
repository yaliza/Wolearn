package by.wolearn.mywords.data

import by.wolearn.core.Resource
import by.wolearn.core.safeApiCall
import by.wolearn.learning.backend.entities.Word
import by.wolearn.mywords.backend.MyWordsApi


class MyWordsRepository(private val myWordsApi: MyWordsApi) {

    suspend fun getWords(): Resource<List<Word>> {
        return safeApiCall { myWordsApi.getWords() }
    }

}