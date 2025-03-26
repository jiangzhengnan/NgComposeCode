package com.health.navigation

import androidx.compose.runtime.*
import com.health.ui.*
import com.health.viewmodel.HealthViewModel

sealed class Screen {
    object Main : Screen()
    object Report : Screen()
    object Settings : Screen()
    object Stats : Screen()
}

@Composable
fun Navigation() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Main) }
    val viewModel = remember { HealthViewModel() }

    when (currentScreen) {
        is Screen.Main -> {
            MainScreen(
                viewModel = viewModel,
                onNavigateToReport = { currentScreen = Screen.Report },
                onNavigateToSettings = { currentScreen = Screen.Settings },
                onNavigateToStats = { currentScreen = Screen.Stats }
            )
        }
        is Screen.Report -> {
            ReportScreen(
                viewModel = viewModel,
                onNavigateBack = { currentScreen = Screen.Main }
            )
        }
        is Screen.Settings -> {
            SettingsScreen(
                viewModel = viewModel,
                onNavigateBack = { currentScreen = Screen.Main }
            )
        }
        is Screen.Stats -> {
            StatsScreen(
                viewModel = viewModel,
                onNavigateBack = { currentScreen = Screen.Main }
            )
        }
    }
} 