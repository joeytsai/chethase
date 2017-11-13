package com.googlejobapp.chethase

import android.app.Application
import android.content.Context
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
}

fun Context.daggerAppComponent(): AppComponent = (applicationContext as Chethase).appComponent