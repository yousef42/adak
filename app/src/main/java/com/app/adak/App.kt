package com.app.adak

import android.animation.Animator
import android.app.Dialog
import android.view.View
import androidx.multidex.MultiDexApplication
import com.app.adak.data.network.AppComponent
import com.app.adak.data.network.DaggerAppComponent
import com.app.adak.data.network.NetModule


class App : MultiDexApplication() {

    companion object {
        lateinit var MyApp: App
        lateinit var appComponent: AppComponent


        fun getApp(): App {
            return MyApp
        }
    }

    override fun onCreate() {
        super.onCreate()

        MyApp = this

        appComponent = DaggerAppComponent.builder().netModule(NetModule()).build()
    }

    fun AppComponent ():AppComponent{
        return appComponent
    }

}