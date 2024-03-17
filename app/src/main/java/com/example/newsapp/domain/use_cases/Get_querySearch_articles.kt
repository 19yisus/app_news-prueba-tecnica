package com.example.newsapp.domain.use_cases

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.New_repository
import javax.inject.Inject

class Get_querySearch_articles @Inject constructor(
	private val repo: New_repository
) {
	
	suspend operator fun invoke(Texto: String, count: Int): List<Article>? {
		val res = repo.Search_new(Texto, count)
		if(res!!.isNotEmpty()) return res
		return listOf(Article())
	}
}