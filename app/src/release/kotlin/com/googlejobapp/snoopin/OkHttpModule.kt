package com.googlejobapp.snoopin

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * Provide HTTP client without debugging
 */
@Module
class OkHttpModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .authenticator(ApplicationOnlyOauth())
            .build()
}