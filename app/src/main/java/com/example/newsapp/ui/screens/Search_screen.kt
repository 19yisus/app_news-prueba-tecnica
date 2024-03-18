package com.example.newsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.newsapp.domain.models.Article
import com.example.newsapp.ui.components.common.NewsCard_component
import com.example.newsapp.ui.viewModel.News_viewModel
import kotlinx.coroutines.launch

class Search_screen: Screen{
	private lateinit var navigator: Navigator
	private lateinit var vm: News_viewModel
	@Composable
	override fun Content() {
		navigator = LocalNavigator.currentOrThrow
		vm = hiltViewModel()
		val listData = vm.ListStateTOpArticles.collectAsState()
		this.screen(listData.value)
	}
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	private fun screen(listData: List<Article>){
		val corrutine = rememberCoroutineScope()
		var textoBusqueeda by remember { mutableStateOf("") }
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
					SearchBar(
						query = textoBusqueeda,
						active = false,
						onQueryChange = {
							textoBusqueeda = it
							corrutine.launch {
								vm.SearchNews(it)
							}
						},
						onActiveChange = {},
						onSearch = {},
						modifier = Modifier
							.fillMaxWidth()
							.padding(10.dp),
						leadingIcon = {
							Icon(imageVector = Icons.Filled.Search, contentDescription = "Icon")
						},
						content = {}
					)
				}
			}
		) {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(it)
			) {
				LazyColumn(modifier = Modifier
					.fillMaxWidth()
					.padding(10.dp)) {
					if(listData.size > 0 && listData[0].title != ""){
						for(Article in listData){
							item {
								NewsCard_component(
									fontS = 17.sp,
									nav = navigator,
									Article = Article,
									modifierCard = Modifier
										.padding(10.dp)
										.fillMaxWidth()
										.height(140.dp)
								)
							}
						}
					}
				}
			}
		}
	}
}