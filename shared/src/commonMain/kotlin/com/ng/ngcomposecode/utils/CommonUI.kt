package com.ng.ngcomposecode.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArrowRightListItem(
	iconRes: Any,
	title: String,
	msgCount: Int? = null,
	valueText: String = "",
	onClick: () -> Unit
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.height(60.dp)
			.clickable {
				onClick.invoke()
			}
	) {

		when (iconRes) {
			is Painter -> {
				Icon(
					painter = iconRes,
					contentDescription = null,
					tint = Color(0xFF018786),
					modifier = Modifier
						.size(40.dp)
						.align(Alignment.CenterVertically)
						.padding(horizontal = 10.dp)
				)
			}

			is ImageVector -> {
				Icon(
					imageVector = iconRes,
					contentDescription = null,
					tint = Color(0xFF018786),
					modifier = Modifier
						.size(40.dp)
						.align(Alignment.CenterVertically)
						.padding(horizontal = 10.dp)
				)
			}
		}
		Row(
			modifier = Modifier
				.align(Alignment.CenterVertically)
				.weight(1f)
		) {
			TextContent(text = title, modifier = Modifier.align(Alignment.CenterVertically))
			if (msgCount != null) {
				Text(
					text = "（$msgCount）",
					fontSize = 12.sp,
					color = Color(0xFFDD302E),
					modifier = Modifier.align(Alignment.CenterVertically)
				)
			}
		}
		if (valueText.isNotEmpty()) {
			TextContent(
				text = valueText,
				modifier = Modifier
					.padding(end = 5.dp)
					.align(Alignment.CenterVertically)
			)
		}
		Icon(
			Icons.Default.KeyboardArrowRight,
			null,
			tint = Color(0xFF888888),
			modifier = Modifier.align(Alignment.CenterVertically)
		)
	}
	Divider()
}


@Composable
fun TextContent(
	text: String,
	modifier: Modifier = Modifier,
	color: Color = Color(0xFF888888),
	maxLines: Int = 99,
	textAlign: TextAlign = TextAlign.Start,
	canCopy: Boolean = false,
	isLoading: Boolean = false
) {
	if (canCopy) {
		SelectionContainer {
			Title(
				title = text,
				modifier = modifier,
				fontSize = 14.sp,
				color = color,
				maxLine = maxLines,
				textAlign = textAlign,
				isLoading = isLoading
			)
		}
	} else {
		Title(
			title = text,
			modifier = modifier,
			fontSize = 14.sp,
			color = color,
			maxLine = maxLines,
			textAlign = textAlign,
			isLoading = isLoading
		)
	}

}

@Composable
fun Title(
	title: String,
	modifier: Modifier = Modifier,
	fontSize: TextUnit,
	color: Color = Color(0xFF888888),
	fontWeight: FontWeight = FontWeight.Normal,
	maxLine: Int = 1,
	textAlign: TextAlign = TextAlign.Start,
	isLoading: Boolean = false
) {
	Text(
		text = title,
		modifier = modifier,
		fontSize = fontSize,
		color = color,
		maxLines = maxLine,
		overflow = TextOverflow.Ellipsis,
		textAlign = textAlign
	)
}