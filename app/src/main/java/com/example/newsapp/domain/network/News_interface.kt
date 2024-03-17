package com.example.newsapp.domain.network

import com.example.newsapp.domain.models.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface News_interface {
	
	@GET("top-headlines")
	suspend fun getTopNewFrom(
		@Query("country") country_reference: String = "us",
		@Query("category") category_reference: String? = null,
		@Query("q") QueryText: String? = null,
		@Query("pageSize") pageCounter: Int = 5
	): Response<ResponseBody>
}