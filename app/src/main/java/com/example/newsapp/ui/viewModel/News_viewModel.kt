package com.example.newsapp.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.use_cases.Get_articlesByCategorie
import com.example.newsapp.domain.use_cases.Get_querySearch_articles
import com.example.newsapp.domain.use_cases.Get_top_articles
import com.example.newsapp.domain.utils.Categories_news
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class News_viewModel @Inject constructor(
	private val useCase_getTop_news: Get_top_articles,
	private val useCase_getByCategorie: Get_articlesByCategorie,
	private val useCase_getByQueryString: Get_querySearch_articles
) :ViewModel() {
	
	private val _ListStateTopArticles: MutableStateFlow<List<Article>> = MutableStateFlow(listOf(Article()))
	val ListStateTOpArticles: StateFlow<List<Article>> =_ListStateTopArticles.asStateFlow()
	
	private val _CountNews: MutableStateFlow<Int> = MutableStateFlow(5)
	val CountNews: StateFlow<Int> = _CountNews.asStateFlow()
	
	init {
		viewModelScope.launch {
			val data = useCase_getTop_news()
			withContext(Dispatchers.IO){
				_ListStateTopArticles.update { data }
			}
		}
	}
	
	suspend fun GetByCategorie(categorie: Categories_news): List<Article>?{
		val data = useCase_getByCategorie(categorie, 100)
		
		if(data.size > 0) return data
		else return listOf()
	}
	
	suspend fun SearchNews(queryTexto: String){
		val data = useCase_getByQueryString(queryTexto, 20)
		withContext(Dispatchers.IO){
			_ListStateTopArticles.update { data!! }
		}
	}
	
	fun updateCount(value: Int) = _CountNews.update { value }
}