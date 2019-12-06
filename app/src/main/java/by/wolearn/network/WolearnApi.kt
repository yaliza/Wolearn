package by.wolearn.network

import by.wolearn.core.model.Token
import by.wolearn.learning.model.entities.Category
import by.wolearn.learning.model.entities.Word
import by.wolearn.login.model.entities.NewUser
import by.wolearn.login.model.entities.UserCredentials
import by.wolearn.profile.model.HistoryWord
import by.wolearn.profile.model.Profile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface WolearnApi {

    @GET(CATEGORIES)
    suspend fun getCategories(): List<Category>

    @POST(CATEGORIES)
    suspend fun setCategories(@Body categories: List<Category>)

    @POST(LOGIN)
    suspend fun login(@Body user: UserCredentials): Token

    @POST(REGISTER)
    suspend fun register(@Body user: NewUser)

    @GET(WORDS)
    suspend fun getWords(): List<Word>

    @GET(USER)
    suspend fun getProfile(): Profile

    @GET("$USER/$HISTORY")
    suspend fun getHistory(@Query(PARAM_OFFSET) offset: Int = 0, @Query(PARAM_NUM) num: Int = 20): List<HistoryWord>
}