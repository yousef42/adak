package com.app.adak.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.app.adak.utils.AppConstant
import okhttp3.OkHttpClient


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
