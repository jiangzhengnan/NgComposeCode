package com.health.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.health.model.*
import com.health.utils.FormatUtils
import com.health.viewmodel.HealthViewModel

@Composable
fun ReportScreen(
    viewModel: HealthViewModel,
    onNavigateBack: () -> Unit
) {
    var weight by remember { mutableStateOf("") }
    var pressure by remember { mutableStateOf("") }
    var mood by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

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
            Text("状态报告", style = MaterialTheme.typography.bodyMedium)
            TextButton(onClick = {
                val currentDate = FormatUtils.getCurrentDate()
                val physicalState = PhysicalState(
                    weight = weight.toDoubleOrNull() ?: 0.0,
                    height = 170.0 // 这里应该从用户配置中获取
                )
                val mentalState = MentalState(
                    pressure = pressure.toIntOrNull() ?: 0,
                    mood = mood.toIntOrNull() ?: 0
                )
                val healthState = HealthState(
                    physicalState = physicalState,
                    mentalState = mentalState,
                    date = currentDate
                )
                viewModel.addHealthState(healthState)
                onNavigateBack()
            }) {
                Text("保存")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 身体状态
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("身体状态", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("体重 (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // 心理状态
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("心理状态", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = pressure,
                    onValueChange = { pressure = it },
                    label = { Text("压力 (0-100)") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = mood,
                    onValueChange = { mood = it },
                    label = { Text("心情 (0-100)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // 备注
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("备注") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
        }
    }
} 