package com.ng.ngcomposecode

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun SharedWelCome(name: String) {
	Text(
		text = "馁好 $name!",
		style = TextStyle(fontSize = 30.sp)

	)
}