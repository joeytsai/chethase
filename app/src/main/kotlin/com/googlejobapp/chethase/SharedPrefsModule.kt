package com.googlejobapp.chethase

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger 2 Module to provide SharedPrefs singleton
 */
@Module
class SharedPrefsModule {
    @Provides
    @Singleton
    fun provideSharedPrefs(context: Context) = SharedPrefs(context)
}