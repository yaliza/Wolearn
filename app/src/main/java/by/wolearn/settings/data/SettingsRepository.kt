package by.wolearn.settings.data

import by.wolearn.auth.AuthPreferences
import by.wolearn.core.safeApiCall
import by.wolearn.core.Resource
import by.wolearn.settings.backend.SettingsApi

class SettingsRepository(
    private val api: SettingsApi
) {

    suspend fun logout(): Resource<Unit> {
        AuthPreferences.clear()
        return Resource.Success(Unit)
    }

    suspend fun resetStatistics(): Resource<Unit> {
        return safeApiCall {
            api.resetStatistics()
        }
    }

    suspend fun deleteProfile(): Resource<Unit> {
        val result = safeApiCall {
            api.deleteProfile()
        }
        if (result is by.wolearn.core.Resource.Success) AuthPreferences.clear()
        return result
    }
}