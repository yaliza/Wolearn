package by.wolearn.registration.data

import by.wolearn.auth.AuthPreferences
import by.wolearn.core.safeApiCall
import by.wolearn.core.Resource
import by.wolearn.registration.backend.RegistrationApi


class RegistrationRepository(
    private val registrationApi: RegistrationApi
) {

    suspend fun register(
        name: String,
        surname: String,
        login: String,
        password: String
    ): by.wolearn.core.Resource<Unit> {
        val result = by.wolearn.core.safeApiCall {
            registrationApi.signUp(prepareRegisterParams(name, surname, login, password))
        }
        return when (result) {
            is by.wolearn.core.Resource.Success -> {
                AuthPreferences.safeJwt(result.data.jwtToken)
                by.wolearn.core.Resource.Success(Unit)
            }
            is by.wolearn.core.Resource.Error.ApiError -> by.wolearn.core.Resource.Error.ApiError(result.exception)
            is by.wolearn.core.Resource.Error.UnknownError -> by.wolearn.core.Resource.Error.UnknownError()
        }
    }

    private fun prepareRegisterParams(
        name: String,
        surname: String,
        login: String,
        password: String
    ): Map<String, String> {
        return mapOf(
            "name" to name,
            "surname" to surname,
            "login" to login,
            "password" to password
        )
    }
}