package com.ng.ngcomposecode.function.read

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.viewmodel.viewModel


@Composable
fun ReadPage() {
	val readVM = viewModel(
		modelClass = ReadViewModel::class,
		creator = {
			ReadViewModel()
		})
	val data by readVM.data

	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text("阅读:$data")
	}

	SideEffect {
		readVM.fetchData()
	}
}