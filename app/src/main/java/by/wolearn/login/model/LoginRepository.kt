package by.wolearn.login.model

import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall


class LoginRepository(val api: WolearnApi) {

    suspend fun login(login: String, password: String) =
        safeApiCall { api.login(UserCredentials(login, password)) }
}