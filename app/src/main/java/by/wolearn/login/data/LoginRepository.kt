package by.wolearn.login.data

import by.wolearn.auth.AuthPreferences
import by.wolearn.core.view.entities.Resource
import by.wolearn.login.backend.LoginApi
import by.wolearn.core.utils.safeApiCall


class LoginRepository(
    private val loginApi: LoginApi
) {

    suspend fun login(login: String, password: String): Resource<Unit> {
        val result = safeApiCall {
            loginApi.signIn(mapOf("name" to login, "password" to password))
        }
        return when (result) {
            is Resource.Success -> {
                AuthPreferences.safeJwt(result.data.jwtToken)
                Resource.Success(Unit)
            }
            is Resource.Error.ApiError -> Resource.Error.ApiError(result.exception)
            is Resource.Error.UnknownError -> Resource.Error.UnknownError()
        }
    }
}