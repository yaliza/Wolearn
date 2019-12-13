package by.wolearn.network

import by.wolearn.core.model.Token
import by.wolearn.learning.model.entities.Category
import by.wolearn.learning.model.entities.MemorizeWord
import by.wolearn.learning.model.entities.Word
import by.wolearn.login.model.entities.NewUser
import by.wolearn.login.model.entities.UserCredentials
import by.wolearn.profile.model.HistoryWord
import by.wolearn.profile.model.Profile
import retrofit2.http.*


interface WolearnApi {

    @GET(CATEGORIES)
    suspend fun getCategories(): List<Category>

    @POST(CATEGORIES)
    suspend fun setCategories(@Body categories: List<Category>)

    @POST(LOGIN)
    suspend fun login(@Body user: UserCredentials): Token

    @POST(REGISTER)
    suspend fun register(@Body user: NewUser)

    @POST(WORD)
    suspend fun saveWord(@Body item: MemorizeWord)

    @GET(LEARN)
    suspend fun getWords(): List<Word>

    @GET(REPEAT)
    suspend fun getRepeatWords(): List<Word>

    @GET(USER)
    suspend fun getProfile(): Profile

    @GET("$USER/$HISTORY")
    suspend fun getHistory(@Query(PARAM_OFFSET) offset: Int = 0, @Query(PARAM_NUM) num: Int = 20): List<HistoryWord>

    @POST("$USER/$RESET_STATISTICS")
    suspend fun resetStatistics()

    @DELETE(USER)
    suspend fun deleteAccount()
}