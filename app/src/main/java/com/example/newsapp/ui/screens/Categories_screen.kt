package com.example.newsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.utils.Categories_news
import com.example.newsapp.ui.components.common.NewsCard_component
import com.example.newsapp.ui.components.common.TopBarWithButtonBack_component
import com.example.newsapp.ui.viewModel.News_viewModel

class Categories_screen(private val Categorie: Categories_news? = null):Screen{
	private lateinit var navigator: Navigator
	
	@Composable
	override fun Content() {
		navigator = LocalNavigator.currentOrThrow
		val vm: News_viewModel = hiltViewModel()
		var listData by remember { mutableStateOf(listOf(Article()))	}
		
		LaunchedEffect(true) {
			val d = vm.GetByCategorie(Categorie!!)
			if(d != null) listData = d
		}
		
		Scaffold(
			topBar = {
				Row(modifier = Modifier
					.fillMaxWidth()
					.background(MaterialTheme.colorScheme.primary)
					.padding(5.dp),
					verticalAlignment = Alignment.CenterVertically
				){
					IconButton(onClick = { navigator.pop()}
					) {
						Icon(
							imageVector = Icons.Filled.ArrowBack,
							contentDescription = "Icono de retorno",
							tint = MaterialTheme.colorScheme.background
						)
					}
					Text(
						text = Categorie.toString(),
						fontWeight = FontWeight.Bold,
						fontSize = 20.sp,
						color = MaterialTheme.colorScheme.background
					)
				}
			}
		) {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(it)
			) {
				LazyColumn(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
					for(Article in listData){
						item {
							NewsCard_component(
								fontS = 17.sp,
								nav = navigator,
								Article = Article,
								modifierCard = Modifier.padding(10.dp).fillMaxWidth().height(140.dp)
							)
						}
					}
				}
			}
		}
	}
}
