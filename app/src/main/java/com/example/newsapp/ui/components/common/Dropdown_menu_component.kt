package com.example.newsapp.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun Dropdown_menu_component(
	value: Int,
	onChange: (Int) -> Unit
) {
	var mExpanded by remember { mutableStateOf(false) }
	val options = listOf(3, 5, 8, 15)
	var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
	
	val icon = if (mExpanded)
		Icons.Filled.KeyboardArrowUp
	else
		Icons.Filled.KeyboardArrowDown
	
	Column(Modifier.padding(10.dp).fillMaxWidth(0.3F)) {
		OutlinedTextField(
			readOnly = true,
			value = value.toString(),
			onValueChange = { onChange(it.toInt()) },
			modifier = Modifier
				.fillMaxWidth()
				.onGloballyPositioned { coordinates ->
					mTextFieldSize = coordinates.size.toSize()
				},
			trailingIcon = {
				Icon(icon,"contentDescription", Modifier.clickable { mExpanded = !mExpanded })
			}
		)
		DropdownMenu(
			expanded = mExpanded,
			onDismissRequest = { mExpanded = false },
			modifier = Modifier
				.width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
		) {
			options.forEach { label ->
				DropdownMenuItem(onClick = {
					onChange(label)
					mExpanded = false
				}, text = { Text(text = label.toString()) })
			}
		}
	}
}