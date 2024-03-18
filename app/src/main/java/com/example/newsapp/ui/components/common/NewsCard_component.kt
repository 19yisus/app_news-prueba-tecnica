package com.example.newsapp.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import coil.compose.AsyncImage
import com.example.newsapp.domain.models.Article
import com.example.newsapp.ui.screens.Details_new_screen

@Composable
fun NewsCard_component(modifierCard: Modifier, fontS: TextUnit, nav: Navigator, Article: Article) {
	var loading: Boolean by remember{ mutableStateOf(false) }
	var Error: Boolean by remember{ mutableStateOf(false) }
	
	Card(
		modifier = modifierCard
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.clickable { nav.push(Details_new_screen(article = Article)) },
			contentAlignment = Alignment.BottomCenter
		) {
			AsyncImage(
				model = Article.urlToImage ?: "",
				contentDescription = "Image",
				modifier = Modifier
					.fillMaxSize(),
				contentScale = ContentScale.Crop,
				onLoading = { loading = true; Error = false; },
				onSuccess = { loading = true; Error = false; },
				onError = { loading = true; Error = true;}
			)
			Row(
				horizontalArrangement = Arrangement.Center,
				modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8F))
			) {
				Text(
					text = Article.title,
					fontWeight = FontWeight.Bold,
					color = Color.White,
					fontSize = fontS,
					textAlign = TextAlign.Center,
					fontFamily = FontFamily.SansSerif,
					modifier = Modifier
						.fillMaxWidth(0.8F)
						.padding(2.dp),
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
			}
			//				if(loading && !Error) CircularProgressIndicator()
			//				if(Error) Text(text = "Ocurrió un error al cargar la imagen")
		}
	}
}