package com.googlejobapp.chethase

import android.app.Activity
import android.app.Application
import android.content.Context
import com.googlejobapp.snoopin.OauthComponent
import com.googlejobapp.snoopin.OauthModule
import timber.log.Timber

/**
 * Initialize the application and manage the object graph
 */
class Chethase : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        Timber.v("here we go")
    }

    lateinit var oauthComponent: OauthComponent

    internal fun initOauthComponent(token: String) {
        oauthComponent = appComponent.oauthComponent(OauthModule(token))
    }
}

fun Context.daggerAppComponent(): AppComponent = (applicationContext as Chethase).appComponent
fun Context.daggerOauthComponent(): OauthComponent = (applicationContext as Chethase).oauthComponent
fun Activity.initDaggerOauthComponent(token: String) = (application as Chethase).initOauthComponent(token)