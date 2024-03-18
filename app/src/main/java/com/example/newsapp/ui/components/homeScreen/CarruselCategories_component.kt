package com.example.newsapp.ui.components.homeScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.utils.Categories_news
import com.example.newsapp.ui.components.common.NewsCard_component
import com.example.newsapp.ui.screens.Categories_screen
import com.example.newsapp.ui.viewModel.News_viewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun CarruselCategories_component(
	dataCategorie: Categories_news,
	vm: News_viewModel,
	nav: Navigator,
	countNews: Int
) {
	var listData by remember { mutableStateOf(listOf(Article()))	}
	
	LaunchedEffect(true) {
		val d = vm.GetByCategorie(dataCategorie)
		if(d != null) listData = d
	}
	
	if(listData.size > 1){
		Text(
			text = "$dataCategorie",
			fontWeight = FontWeight.SemiBold,
			color = MaterialTheme.colorScheme.primary,
			fontSize = 18.sp,
			textDecoration = TextDecoration.Underline,
			modifier = Modifier
				.padding(horizontal = 10.dp)
				.clickable { nav.push(Categories_screen(dataCategorie)) }
		)
		
		LazyRow(modifier = Modifier.fillMaxWidth()) {
			for(x in 0 until countNews){
				item {
					NewsCard_component(
						fontS = 10.sp,
						nav = nav,
						Article = listData[x],
						modifierCard = Modifier
							.padding(10.dp)
							.width(130.dp)
							.height(70.dp)
					)
				}
			}
		}
		Spacer(modifier = Modifier.padding(bottom = 20.dp))
	}
}