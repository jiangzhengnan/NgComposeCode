package com.health.viewmodel

import com.health.model.UserProfile
import com.health.mvi.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel(
    private val healthViewModel: HealthViewModel
) : MviModel<SettingsState, SettingsIntent, SettingsEffect> {
    private val _state = MutableStateFlow(SettingsState())
    override val state: StateFlow<SettingsState> = _state

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        val userProfile = healthViewModel.state.value
        if (userProfile is MainState.Success) {
            userProfile.userProfile?.let { profile ->
                _state.value = SettingsState(
                    name = profile.name,
                    birthDate = profile.birthDate,
                    gender = profile.gender,
                    height = profile.height?.toString() ?: "",
                    targetWeight = profile.targetWeight?.toString() ?: "",
                    targetBMI = profile.targetBMI?.toString() ?: ""
                )
            }
        }
    }

    override fun processIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.UpdateName -> updateName(intent.name)
            is SettingsIntent.UpdateBirthDate -> updateBirthDate(intent.birthDate)
            is SettingsIntent.UpdateGender -> updateGender(intent.gender)
            is SettingsIntent.UpdateHeight -> updateHeight(intent.height)
            is SettingsIntent.UpdateTargetWeight -> updateTargetWeight(intent.weight)
            is SettingsIntent.UpdateTargetBMI -> updateTargetBMI(intent.bmi)
            is SettingsIntent.SaveSettings -> saveSettings()
            is SettingsIntent.NavigateBack -> processEffect(SettingsEffect.NavigateBack)
            is SettingsIntent.ShowAboutApp -> processEffect(SettingsEffect.ShowAboutDialog)
        }
    }

    override fun processEffect(effect: SettingsEffect) {
        // 在实际应用中，这里会处理导航和显示提示等副作用
    }

    private fun updateName(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    private fun updateBirthDate(birthDate: String) {
        _state.value = _state.value.copy(birthDate = birthDate)
    }

    private fun updateGender(gender: String) {
        _state.value = _state.value.copy(gender = gender)
    }

    private fun updateHeight(height: String) {
        _state.value = _state.value.copy(height = height)
    }

    private fun updateTargetWeight(weight: String) {
        _state.value = _state.value.copy(targetWeight = weight)
    }

    private fun updateTargetBMI(bmi: String) {
        _state.value = _state.value.copy(targetBMI = bmi)
    }

    private fun saveSettings() {
        val currentState = _state.value
        val profile = UserProfile(
            name = currentState.name,
            birthDate = currentState.birthDate,
            gender = currentState.gender,
            height = currentState.height.toDoubleOrNull(),
            targetWeight = currentState.targetWeight.toDoubleOrNull(),
            targetBMI = currentState.targetBMI.toDoubleOrNull()
        )

        healthViewModel.updateUserProfile(profile)
        processEffect(SettingsEffect.ShowToast("设置已保存"))
        processEffect(SettingsEffect.NavigateBack)
    }
} 