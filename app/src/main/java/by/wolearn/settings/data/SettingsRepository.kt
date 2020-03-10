package by.wolearn.settings.data

import by.wolearn.auth.AuthPreferences
import by.wolearn.core.utils.safeApiCall
import by.wolearn.core.view.entities.Resource
import by.wolearn.settings.backend.SettingsApi

class SettingsRepository(
    private val api: SettingsApi
) {

    suspend fun logout(): Resource<Unit> {
        AuthPreferences.clear()
        return Resource.Success(Unit)
    }

    suspend fun deleteProfile(): Resource<Unit> {
        val result = safeApiCall {
            api.deleteProfile()
        }
        if (result is Resource.Success) AuthPreferences.clear()
        return result
    }
}