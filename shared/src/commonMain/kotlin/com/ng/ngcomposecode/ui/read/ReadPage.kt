package com.ng.ngcomposecode.ui.read

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter


@Composable
fun ReadPage() {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text("阅读")
	}
}