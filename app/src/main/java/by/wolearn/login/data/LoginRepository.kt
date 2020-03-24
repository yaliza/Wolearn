package by.wolearn.login.data

import by.wolearn.auth.AuthPreferences
import by.wolearn.auth.LoginApi


class LoginRepository(private val loginApi: LoginApi) {

    suspend fun login(login: String, password: String): by.wolearn.core.Resource<Unit> {
        val result = by.wolearn.core.safeApiCall {
            loginApi.signIn(mapOf("name" to login, "password" to password))
        }
        return when (result) {
            is by.wolearn.core.Resource.Success -> {
                AuthPreferences.safeJwt(result.data.jwtToken)
                by.wolearn.core.Resource.Success(Unit)
            }
            is by.wolearn.core.Resource.Error.ApiError -> by.wolearn.core.Resource.Error.ApiError(
                result.exception
            )
            is by.wolearn.core.Resource.Error.UnknownError -> by.wolearn.core.Resource.Error.UnknownError()
        }
    }
}