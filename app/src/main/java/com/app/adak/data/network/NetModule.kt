package com.app.adak.data.network

import com.app.adak.data.network.services.ApiService

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context
import com.app.adak.utils.AppConstant


@Module
internal class NetModule() {


    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(AppConstant.BASE_URL)
            .client(okHttpClient)
            .build()
    }

}
