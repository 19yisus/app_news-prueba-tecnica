package com.example.newsapp

import android.app.Application
import android.content.Intent
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.utils.Navitation_config
import com.example.newsapp.ui.screens.Categories_screen
import com.example.newsapp.ui.screens.Details_new_screen
import com.example.newsapp.ui.screens.Home_screen
import com.example.newsapp.ui.screens.Search_screen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application(){
	
	override fun onCreate() {
		super.onCreate()
		ScreenRegistry{
			register<Navitation_config.HomeScreen> { Home_screen() }
			register<Navitation_config.DetailsScreen> { Details_new_screen(article = Article()) }
			register<Navitation_config.SearchScreen> { Search_screen() }
			register<Navitation_config.CategorieScreen> { Categories_screen() }
		}
	}
}