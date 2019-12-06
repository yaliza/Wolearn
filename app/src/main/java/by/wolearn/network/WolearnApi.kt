package by.wolearn.network

import by.wolearn.core.model.Token
import by.wolearn.learning.model.entities.Category
import by.wolearn.learning.model.entities.Word
import by.wolearn.login.model.UserCredentials
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface WolearnApi {

    @GET(CATEGORIES)
    suspend fun getCategories(): List<Category>

    @POST(CATEGORIES)
    suspend fun setCategories(@Body categories: List<Category>)

    @POST(LOGIN)
    suspend fun login(@Body user: UserCredentials): Token

//    @POST(REGISTER)
//    suspend fun register(@Body user: User)

    @GET(WORDS)
    suspend fun getWords(): List<Word>
}