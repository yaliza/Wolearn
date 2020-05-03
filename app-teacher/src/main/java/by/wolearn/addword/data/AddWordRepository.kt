package by.wolearn.addword.data

import by.wolearn.addword.backend.PartOfSpeechApi
import by.wolearn.addword.backend.WordInsertApi
import by.wolearn.addword.backend.entities.PartOfSpeech
import by.wolearn.addword.backend.entities.WordInsert
import by.wolearn.core.Resource
import by.wolearn.core.safeApiCall


class AddWordRepository(
    private val partOfSpeechApi: PartOfSpeechApi,
    private val wordInsertApi: WordInsertApi
) {

    suspend fun getPartOfSpeeches(): Resource<List<PartOfSpeech>> {
        return safeApiCall { partOfSpeechApi.getPartOfSpeeches() }
    }

    suspend fun wordInsert(wordInsert: WordInsert): Resource<Unit> {
        return safeApiCall { wordInsertApi.insertWord(wordInsert) }
    }
}