package com.googlejobapp.chethase

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton


/**
 * Initialize dependencies only valid in debug builds.
 */
@Module
class AppModule(private val app: Application) {

    init {
        Stetho.initializeWithDefaults(app)

        // Make Logcat clickable
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(e: StackTraceElement) = "(${e.fileName}:${e.lineNumber})"
        })
    }

    @Provides
    @Singleton
    fun provideContext(): Context = app
}