package com.example.newsapp.domain.repository

import android.util.Log
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.network.News_interface
import com.example.newsapp.domain.utils.Categories_news
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class New_repository @Inject constructor(
	private val api_news: Retrofit
) {
	
	suspend fun Get_topNews(): List<Article>?{
		val res = api_news.create(News_interface::class.java).getTopNewFrom()
		if(res.isSuccessful){
			println(res.body())
			if(res.body() != null) return res.body()!!.articles
		}
		return listOf()
	}
	
	suspend fun Get_NewByCategories(categorie_ref: Categories_news, count: Int): List<Article>?{
		val res = api_news.create(News_interface::class.java).getTopNewFrom(
			category_reference = categorie_ref.toString(),
			pageCounter = count
		)
		if(res.isSuccessful){
			println(res.body())
			if(res.body() != null) return res.body()!!.articles
		}
		return listOf()
	}
	
	suspend fun Search_new(Texto: String, count: Int): List<Article> {
		val res = api_news.create(News_interface::class.java).getTopNewFrom(
			QueryText = Texto,
			pageCounter = count
		)
		if(res.isSuccessful){
			println(res.body())
			if(res.body() != null) return res.body()!!.articles
		}
		return listOf()
	}
}