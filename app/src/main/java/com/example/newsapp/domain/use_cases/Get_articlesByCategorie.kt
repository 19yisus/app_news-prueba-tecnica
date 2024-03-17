package com.example.newsapp.domain.use_cases

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.New_repository
import com.example.newsapp.domain.utils.Categories_news
import javax.inject.Inject

class Get_articlesByCategorie @Inject constructor(
	private val repo: New_repository
) {
	
	suspend operator fun invoke(categorie: Categories_news, count: Int): List<Article>{
		val res = repo.Get_NewByCategories(categorie, count)
		if(res!!.isNotEmpty()) return res
		return listOf(Article())
	}
}