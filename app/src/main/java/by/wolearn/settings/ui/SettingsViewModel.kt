package by.wolearn.settings.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.wolearn.R
import by.wolearn.core.Event
import by.wolearn.core.fold
import by.wolearn.settings.data.SettingsRepository
import by.wolearn.settings.ui.entities.Item
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    val state = MutableLiveData<State>()
    val showLearningMode = MutableLiveData<Event<Unit>>()
    val snackBarInfo = MutableLiveData<Event<Int>>()

    init {
        val list = listOf(
            Item.Label(R.string.settings_title_profile),
            Item.Section(
                SECTION_PROFILE_ID,
                listOf(
                    Item.Button(
                        EDIT_PROFILE_ID,
                        R.string.settings_edit_profile,
                        R.drawable.settings_ic_edit
                    ),
                    Item.Button(
                        DELETE_ACCOUNT_ID,
                        R.string.settings_delete_account,
                        R.drawable.settings_ic_delete
                    )
                )
            ),
            Item.Label(R.string.settings_title_learning),
            Item.Section(
                SECTION_LEARNING_ID,
                listOf(
                    Item.Button(
                        RESET_STATISTICS_ID,
                        R.string.settings_reset_statistics,
                        R.drawable.settings_ic_remove
                    ),
                    Item.Button(
                        LEARNING_MODE_ID,
                        R.string.settings_learning_mode,
                        R.drawable.settings_ic_settings
                    )
                )
            ),
            Item.Label(R.string.settings_title_others),
            Item.Section(
                SECTION_OTHERS_ID,
                listOf(
                    Item.Button(LOGOUT_ID, R.string.settings_logout, R.drawable.settings_ic_exit)
                )
            )
        )
        state.value = State.Success(list)
    }

    fun onButtonClick(id: String) {
        when (id) {
            LEARNING_MODE_ID -> showLearningMode.postValue(Event(Unit))
            LOGOUT_ID -> logout()
            DELETE_ACCOUNT_ID -> deleteProfile()
            RESET_STATISTICS_ID -> resetStatistics()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            state.postValue(State.Progress)
            repository.logout().fold(
                { state.postValue(State.Logout) },
                { }
            )
        }
    }

    private fun deleteProfile() {
        viewModelScope.launch {
            state.postValue(State.Progress)
            repository.deleteProfile().fold(
                { state.postValue(State.Logout) },
                { }
            )
        }
    }

    private fun resetStatistics() {
        viewModelScope.launch {
            state.postValue(State.Progress)
            repository.resetStatistics().fold(
                { snackBarInfo.postValue(Event(R.string.setting_reset_statistics_success)) },
                {}
            )
        }
    }

    sealed class State {
        data class Success(val items: List<Item>) : State()
        object Logout : State()
        object Progress : State()
    }

    companion object {
        const val SECTION_OTHERS_ID = "others_id"
        const val SECTION_PROFILE_ID = "profile_id"
        const val SECTION_LEARNING_ID = "learning_id"
        const val EDIT_PROFILE_ID = "profile_id"
        const val RESET_STATISTICS_ID = "reset_stat_id"
        const val DELETE_ACCOUNT_ID = "delete_id"
        const val LEARNING_MODE_ID = "mode_id"
        const val LOGOUT_ID = "logout_id"
    }

}