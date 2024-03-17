package com.example.newsapp.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBarWithButtonBack_component(texto: String? = "",NavBack: () -> Boolean) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	){
		IconButton(onClick = { NavBack() }
		) {
			Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Icono de retorno")
		}
		Text(text = texto ?: "News", fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 10.dp))
	}
}