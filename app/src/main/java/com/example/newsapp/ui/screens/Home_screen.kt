package com.example.newsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.newsapp.domain.utils.Categories_news
import com.example.newsapp.domain.utils.Navitation_config
import com.example.newsapp.ui.components.common.Dropdown_menu_component
import com.example.newsapp.ui.components.common.TopBarWithButton_component
import com.example.newsapp.ui.components.homeScreen.CarruselCategories_component
import com.example.newsapp.ui.components.homeScreen.Carrusel_component
import com.example.newsapp.ui.viewModel.News_viewModel

class Home_screen: Screen{
	private lateinit var navigator: Navigator
	private lateinit var Screen_Search: Screen
	
	@Composable
	override fun Content() {
		navigator = LocalNavigator.currentOrThrow
		Screen_Search = rememberScreen(provider = Navitation_config.SearchScreen)
		val vm: News_viewModel = hiltViewModel()
		val articulos = vm.ListStateTOpArticles.collectAsState()
		val countNews = vm.CountNews.collectAsState()
		
		Scaffold(
			topBar = { TopBarWithButton_component { navigator.push(Screen_Search) } }
		) {
			Box(modifier = Modifier
				.fillMaxSize()
				.padding(it)){
				LazyColumn(
					modifier = Modifier.fillMaxSize().padding(vertical = 10.dp, horizontal = 10.dp)
				){
					
					item {
						Carrusel_component(articulos.value, navigator)
						Spacer(modifier = Modifier.padding(bottom = 20.dp))
					}
					
//					item {
//						Dropdown_menu_component(
//							value = countNews.value,
//							onChange = { vm.updateCount(it) }
//						)
//					}
					
					for(dataCategorie in Categories_news.values()){
						item {
							CarruselCategories_component(dataCategorie, vm, navigator, countNews.value)
						}
					}
				}
			}
		}
	}
}