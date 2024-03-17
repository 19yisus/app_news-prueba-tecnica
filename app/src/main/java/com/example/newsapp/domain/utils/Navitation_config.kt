package com.example.newsapp.domain.utils

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class Navitation_config: ScreenProvider {
	data object HomeScreen: Navitation_config()
	data object DetailsScreen: Navitation_config()
	data object SearchScreen: Navitation_config()
	data object CategorieScreen: Navitation_config()
}
