package com.example.newsapp.domain.di

import android.content.Context
import com.example.newsapp.domain.utils.Constants.API_KEY
import com.example.newsapp.domain.utils.Constants.API_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
	
	private val OkConfigHTTP: OkHttpClient = OkHttpClient.Builder()
		.addInterceptor {
			val originalRequest = it.request()
			val newRequestBuilder = originalRequest.newBuilder().header("Authorization", "Bearer " + API_KEY).build()
			it.proceed(newRequestBuilder)
		}.build()

	@Provides
	@Singleton
	fun provideApiNew(@ApplicationContext app: Context): Retrofit = Retrofit.Builder()
		.baseUrl(API_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.client(OkConfigHTTP)
		.build()
	
	@Provides
	@Singleton
	fun provideGson(): Gson = Gson()
}