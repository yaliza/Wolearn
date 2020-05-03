package by.wolearn.login.data

import by.wolearn.auth.AuthPreferences
import by.wolearn.auth.LoginApi
import by.wolearn.core.Resource
import by.wolearn.core.safeApiCall


class LoginRepository(val loginApi: LoginApi) {

    suspend fun login(login: String, password: String): Resource<Unit> {
        val result = safeApiCall {
            loginApi.signIn(mapOf("name" to login, "password" to password, "isTeacher" to "true"))
        }
        return when (result) {
            is Resource.Success -> {
                AuthPreferences.safeJwt(result.data.jwtToken)
                Resource.Success(Unit)
            }
            is Resource.Error.ApiError -> Resource.Error.ApiError(result.exception)
            is Resource.Error.UnknownError -> Resource.Error.UnknownError(result.error)
        }
    }

}