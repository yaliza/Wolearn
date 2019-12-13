package by.wolearn.login.model.repositories

import by.wolearn.core.model.TokenStorage
import by.wolearn.core.view.entities.Resource
import by.wolearn.core.view.entities.mapResource
import by.wolearn.login.model.entities.NewUser
import by.wolearn.login.model.entities.UserCredentials
import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall

class RegistrationRepository(
    val api: WolearnApi,
    val tokenStorage: TokenStorage
) {

    suspend fun register(newUser: NewUser): Resource<Unit> {
        val resp = safeApiCall { api.register(newUser) }
        if (resp !is Resource.Success) {
            return resp
        }
        val respLogin = safeApiCall { api.login(UserCredentials(newUser.login, newUser.password)) }
        if (respLogin is Resource.Success) {
            respLogin.data?.let { tokenStorage.store(it) }
        }
        return respLogin.mapResource { Unit }
    }
}