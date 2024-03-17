package com.example.newsapp.domain.use_cases

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.New_repository
import javax.inject.Inject

class Get_top_articles @Inject constructor(
	private val repo: New_repository
) {
	
	suspend operator fun invoke(): List<Article>{
		val news_article = repo.Get_topNews()
		if(news_article!!.isNotEmpty()) return news_article
		return listOf(Article(source = null))
	}
}