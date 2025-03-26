package com.health.mvi

import com.health.model.HealthState
import com.health.model.UserProfile
import kotlinx.datetime.LocalDate

sealed class MainState {
    object Loading : MainState()
    data class Success(
        val currentDate: LocalDate,
        val userProfile: UserProfile?,
        val healthStates: List<HealthState>,
        val selectedView: ViewType = ViewType.Circle
    ) : MainState()
    data class Error(val message: String) : MainState()
}

enum class ViewType {
    Circle, List
}

sealed class MainIntent {
    object LoadData : MainIntent()
    object ToggleView : MainIntent()
    data class SelectDate(val date: LocalDate) : MainIntent()
    object NavigateToReport : MainIntent()
    object NavigateToStats : MainIntent()
    object NavigateToSettings : MainIntent()
}

sealed class MainEffect {
    data class ShowError(val message: String) : MainEffect()
    object NavigateToReport : MainEffect()
    object NavigateToStats : MainEffect()
    object NavigateToSettings : MainEffect()
    data class ShowToast(val message: String) : MainEffect()
} 