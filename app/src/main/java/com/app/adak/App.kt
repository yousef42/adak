package com.app.adak

import android.animation.Animator
import android.app.Dialog
import android.view.View
import androidx.multidex.MultiDexApplication
import com.app.adak.data.network.AppComponent
import com.app.adak.data.network.DaggerAppComponent
import com.app.adak.data.network.NetModule
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatDelegate
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext


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
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        appComponent = DaggerAppComponent.builder().netModule(NetModule()).build()


        try {
           ProviderInstaller.installIfNeeded(applicationContext)
            val sslContext: SSLContext = SSLContext.getInstance("TLSv1.2")
            sslContext.init(null, null, null)
            sslContext.createSSLEngine()
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }

    }

    fun AppComponent(): AppComponent {
        return appComponent
    }

}