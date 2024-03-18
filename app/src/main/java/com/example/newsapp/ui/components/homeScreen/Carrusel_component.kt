package com.example.newsapp.ui.components.homeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import coil.compose.AsyncImage
import com.example.newsapp.domain.models.Article
import com.example.newsapp.ui.screens.Details_new_screen
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carrusel_component(articulos: List<Article?>, nav: Navigator){
	val pagerState = rememberPagerState(pageCount = {
		articulos.size
	})
	
	LaunchedEffect(Unit) {
		while (true) {
			delay(3000) // Esperar 5 segundos
			val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
			pagerState.animateScrollToPage(nextPage)
		}
	}
	
	ElevatedCard(
		modifier = Modifier
			.fillMaxWidth()
			.defaultMinSize(minHeight = 150.dp),
		shape = RoundedCornerShape(10.dp)
	){
		HorizontalPager(state = pagerState) { page ->
			Box(
				modifier = Modifier
					.fillMaxSize()
					.clickable { nav.push(Details_new_screen(article = articulos[pagerState.currentPage]!!)) },
				contentAlignment = Alignment.BottomCenter
			){
				AsyncImage(
					model = articulos[pagerState.currentPage]?.urlToImage ?: "",
					contentDescription = "Image",
					modifier = Modifier
						.aspectRatio(16f / 9f)
						.fillMaxSize()
						.scale(1.2F)
				)
				Row(
					horizontalArrangement = Arrangement.Center,
					modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8F)).padding(4.dp)
				){
					Text(
						text = articulos[pagerState.currentPage]?.title ?: "",
						textAlign = TextAlign.Center,
						color = Color.White,
						fontSize = 18.sp,
						fontWeight = FontWeight.SemiBold,
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
						modifier = Modifier.fillMaxWidth(0.8F)
					)
				}
			}
		}
		Row(
			Modifier
				.wrapContentHeight()
				.fillMaxWidth()
				.padding(bottom = 8.dp),
			horizontalArrangement = Arrangement.Center
		) {
			repeat(pagerState.pageCount) { iteration ->
				val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
				Box(
					modifier = Modifier
						.padding(2.dp)
						.clip(CircleShape)
						.background(color)
						.size(8.dp)
				)
			}
		}
	}
}