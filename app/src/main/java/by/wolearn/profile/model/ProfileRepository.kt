package by.wolearn.profile.model

import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall


class ProfileRepository(private val api: WolearnApi) {

    suspend fun getProfile() = safeApiCall { api.getProfile() }
    suspend fun getHistory() = safeApiCall { api.getHistory() }

}