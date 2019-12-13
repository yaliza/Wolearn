package by.wolearn.login.model.repositories

import by.wolearn.core.model.TokenStorage
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.mapResource
import by.wolearn.login.model.entities.UserCredentials
import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall


class LoginRepository(
    private val api: WolearnApi,
    private val tokenStorage: TokenStorage
) {

    suspend fun login(login: String, password: String): Resource<Unit> {
        val response = safeApiCall { api.login(
            UserCredentials(
                login,
                password
            )
        ) }
        if (response is Resource.Success && response.data != null) {
            tokenStorage.store(response.data)
        }
        return response.mapResource { Unit }
    }

}