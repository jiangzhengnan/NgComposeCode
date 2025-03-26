package com.health.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.health.model.HealthState
import com.health.mvi.MainState
import com.health.utils.FormatUtils
import com.health.viewmodel.HealthViewModel
import kotlinx.datetime.*

@Composable
fun MainScreen(
    viewModel: HealthViewModel,
    onNavigateToReport: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToStats: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    when (state) {
        is MainState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is MainState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text((state as MainState.Error).message)
            }
        }
        is MainState.Success -> {
            val successState = state as MainState.Success
            MainScreenContent(
                state = successState,
                onNavigateToReport = onNavigateToReport,
                onNavigateToSettings = onNavigateToSettings,
                onNavigateToStats = onNavigateToStats
            )
        }
    }
}

@Composable
private fun MainScreenContent(
    state: MainState.Success,
    onNavigateToReport: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToStats: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 顶部栏
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "< ${FormatUtils.formatDate(state.currentDate)} >",
                style = MaterialTheme.typography.headlineMedium
            )
            IconButton(onClick = onNavigateToSettings) {
                Icon(Icons.Default.Settings, contentDescription = "设置")
            }
        }

        // 状态圆环
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // 用户名和BMI状态
                state.userProfile?.let { profile ->
                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // 体重和BMI信息
                val latestState = state.healthStates.firstOrNull()
                Text(
                    text = "体重: ${latestState?.physicalState?.weight?.let { FormatUtils.formatDouble(it) } ?: "--"} kg\n" +
                            "BMI: ${latestState?.physicalState?.bmi?.let { FormatUtils.formatDouble(it) } ?: "0.0"}"
                )
            }
        }

        // 底部状态记录列表
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(state.healthStates) { healthState ->
                HealthStateItem(healthState)
            }
        }

        // 底部按钮栏
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 列表按钮
            FilledTonalIconButton(
                onClick = { /* TODO: 切换到列表视图 */ }
            ) {
                Icon(Icons.Default.List, contentDescription = "列表")
            }
            
            // 添加记录按钮
            FilledIconButton(
                onClick = onNavigateToReport,
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "添加记录",
                    modifier = Modifier.size(32.dp)
                )
            }
            
            // 统计按钮
            FilledTonalIconButton(
                onClick = onNavigateToStats
            ) {
                Icon(Icons.Default.Build, contentDescription = "统计")
            }
        }
    }
}

@Composable
fun HealthStateItem(state: HealthState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("心理状态")
                Text("压力: ${state.mentalState.pressure}")
                Text("心情: ${state.mentalState.mood}")
            }
            Column {
                Text("身体状态")
                Text("体重: ${FormatUtils.formatDouble(state.physicalState.weight)}kg")
                Text("BMI: ${FormatUtils.formatDouble(state.physicalState.bmi)}")
            }
        }
    }
} 