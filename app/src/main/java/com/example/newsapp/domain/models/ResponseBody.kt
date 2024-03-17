package com.example.newsapp.domain.models

data class ResponseBody(
	val status: String,
	val totalResults: Int,
	val articles: List<Article>
)
