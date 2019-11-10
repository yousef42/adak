package com.app.adak.data.network

import com.app.adak.App
import com.app.adak.ui.home.HomeActivity
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Component(modules = [NetModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: HomeActivity)
    fun getRetrofit(): Retrofit
    //fun getOkHttpClient(): OkHttpClient

}