package com.googlejobapp.snoopin

import dagger.Module
import dagger.Provides

/**
 * Dagger 2 Module to provide OkHttp OauthInterceptor
 */
@Module
class OauthModule(private val token: String) {

    @Provides
    @OauthScope
    fun provideOauthInterceptor() = OauthInterceptor(token)
}