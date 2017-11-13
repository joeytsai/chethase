package com.googlejobapp.snoopin

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * Provide HTTP client without debugging
 */
@Module
class HttpModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .authenticator(ApplicationOnlyOAuth())
            .build()
}