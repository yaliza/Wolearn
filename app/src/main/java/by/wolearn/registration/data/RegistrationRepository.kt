package by.wolearn.registration.data

import by.wolearn.auth.AuthPreferences
import by.wolearn.core.utils.safeApiCall
import by.wolearn.core.view.entities.Resource
import by.wolearn.registration.backend.RegistrationApi


class RegistrationRepository(
    private val registrationApi: RegistrationApi
) {

    suspend fun register(
        name: String,
        surname: String,
        login: String,
        password: String
    ): Resource<Unit> {
        val result = safeApiCall {
            registrationApi.signUp(prepareRegisterParams(name, surname, login, password))
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