package com.health.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.health.utils.FormatUtils
import com.health.viewmodel.HealthViewModel

@Composable
fun StatsScreen(
    viewModel: HealthViewModel,
    onNavigateBack: () -> Unit
) {
    val healthStates by viewModel.healthStates.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }

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
            IconButton(onClick = onNavigateBack) {
                Text("←")
            }
            Text("统计", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 标签选择
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            ) {
                Text("身体状态")
            }
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            ) {
                Text("心理状态")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 图表区域
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val width = size.width
                    val height = size.height
                    val dataPoints = if (selectedTab == 0) {
                        // 体重数据
                        healthStates.map { it.physicalState.weight }
                    } else {
                        // 心理状态数据（这里用压力值作为示例）
                        healthStates.map { it.mentalState.pressure.toDouble() }
                    }

                    if (dataPoints.isNotEmpty()) {
                        val xStep = width / (dataPoints.size - 1)
                        val maxY = dataPoints.maxOrNull() ?: 0.0
                        val minY = dataPoints.minOrNull() ?: 0.0
                        val yRange = maxY - minY
                        
                        // 绘制折线
                        for (i in 0 until dataPoints.size - 1) {
                            val startX = i * xStep
                            val startY = height * (1 - (dataPoints[i] - minY) / yRange)
                            val endX = (i + 1) * xStep
                            val endY = height * (1 - (dataPoints[i + 1] - minY) / yRange)
                            
                            drawLine(
                                color = Color.Blue,
                                start = Offset(startX, startY.toFloat()),
                                end = Offset(endX, endY.toFloat()),
                                strokeWidth = 3f
                            )
                        }
                    }
                }
            }
        }

        // 时间范围选择
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(onClick = { /* TODO */ }) {
                Text("日")
            }
            TextButton(onClick = { /* TODO */ }) {
                Text("周")
            }
            TextButton(onClick = { /* TODO */ }) {
                Text("月")
            }
            TextButton(onClick = { /* TODO */ }) {
                Text("年")
            }
        }

        // 统计数据
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (selectedTab == 0) {
                    Text("体重统计", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    val avgWeight = healthStates.map { it.physicalState.weight }.average()
                    val maxWeight = healthStates.maxOfOrNull { it.physicalState.weight } ?: 0.0
                    val minWeight = healthStates.minOfOrNull { it.physicalState.weight } ?: 0.0
                    Text("平均体重: ${FormatUtils.formatDouble(avgWeight)} kg")
                    Text("最大体重: ${FormatUtils.formatDouble(maxWeight)} kg")
                    Text("最小体重: ${FormatUtils.formatDouble(minWeight)} kg")
                } else {
                    Text("心理状态统计", style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    val avgPressure = healthStates.map { it.mentalState.pressure }.average()
                    val avgMood = healthStates.map { it.mentalState.mood }.average()
                    Text("平均压力值: ${FormatUtils.formatDouble(avgPressure)}")
                    Text("平均心情值: ${FormatUtils.formatDouble(avgMood)}")
                }
            }
        }
    }
} 