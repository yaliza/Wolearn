package by.wolearn.login.model.repositories

import by.wolearn.login.model.entities.NewUser
import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall

class RegistrationRepository(
    val api: WolearnApi
) {

    suspend fun register(newUser: NewUser) = safeApiCall { api.register(newUser) }
}