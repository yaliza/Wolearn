package by.wolearn.profile.data

import by.wolearn.core.Resource
import by.wolearn.core.safeApiCall
import by.wolearn.profile.backend.HistoryApi
import by.wolearn.profile.backend.ProfileApi
import by.wolearn.profile.backend.entities.HistoryWord
import by.wolearn.profile.backend.entities.Profile


class ProfileRepository(
    private val profileApi: ProfileApi,
    private val historyApi: HistoryApi
) {

    suspend fun getProfile(): by.wolearn.core.Resource<Profile> {
        return by.wolearn.core.safeApiCall { profileApi.getProfile() }
    }

    suspend fun getHistory(): by.wolearn.core.Resource<List<HistoryWord>> {
        return by.wolearn.core.safeApiCall { historyApi.getHistory() }
    }

}