package com.health.mvi

data class SettingsState(
    val name: String = "",
    val birthDate: String = "",
    val gender: String = "",
    val height: String = "",
    val targetWeight: String = "",
    val targetBMI: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class SettingsIntent {
    data class UpdateName(val name: String) : SettingsIntent()
    data class UpdateBirthDate(val birthDate: String) : SettingsIntent()
    data class UpdateGender(val gender: String) : SettingsIntent()
    data class UpdateHeight(val height: String) : SettingsIntent()
    data class UpdateTargetWeight(val weight: String) : SettingsIntent()
    data class UpdateTargetBMI(val bmi: String) : SettingsIntent()
    object SaveSettings : SettingsIntent()
    object NavigateBack : SettingsIntent()
    object ShowAboutApp : SettingsIntent()
}

sealed class SettingsEffect {
    data class ShowError(val message: String) : SettingsEffect()
    object NavigateBack : SettingsEffect()
    object ShowAboutDialog : SettingsEffect()
    data class ShowToast(val message: String) : SettingsEffect()
} 