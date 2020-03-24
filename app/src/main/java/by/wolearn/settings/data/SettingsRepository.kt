package by.wolearn.settings.data

import by.wolearn.auth.AuthPreferences
import by.wolearn.core.safeApiCall
import by.wolearn.core.Resource
import by.wolearn.settings.backend.SettingsApi

class SettingsRepository(
    private val api: SettingsApi
) {

    suspend fun logout(): by.wolearn.core.Resource<Unit> {
        AuthPreferences.clear()
        return by.wolearn.core.Resource.Success(Unit)
    }

    suspend fun deleteProfile(): by.wolearn.core.Resource<Unit> {
        val result = by.wolearn.core.safeApiCall {
            api.deleteProfile()
        }
        if (result is by.wolearn.core.Resource.Success) AuthPreferences.clear()
        return result
    }
}