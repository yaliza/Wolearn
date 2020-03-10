package by.wolearn.profile.data

import by.wolearn.core.view.entities.Resource
import by.wolearn.core.utils.safeApiCall
import by.wolearn.profile.backend.HistoryApi
import by.wolearn.profile.backend.ProfileApi
import by.wolearn.profile.backend.entities.HistoryWord
import by.wolearn.profile.backend.entities.Profile


class ProfileRepository(
    private val profileApi: ProfileApi,
    private val historyApi: HistoryApi
) {

    suspend fun getProfile(): Resource<Profile> {
        return safeApiCall { profileApi.getProfile() }
    }

    suspend fun getHistory(): Resource<List<HistoryWord>> {
        return safeApiCall { historyApi.getHistory() }
    }

}