package by.wolearn.settings.model

import by.wolearn.core.model.TokenStorage
import by.wolearn.network.WolearnApi
import by.wolearn.network.safeApiCall


class SettingsRepository(
    private val api: WolearnApi,
    private val tokenStorage: TokenStorage
) {

    suspend fun resetStat() = safeApiCall { api.resetStatistics() }

    fun logout() {
        tokenStorage.delete()
    }
}