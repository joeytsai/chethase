package com.googlejobapp.snoopin

import dagger.Module
import dagger.Provides

/**
 * Created by joey on 11/12/17.
 */
@Module
class OauthModule(private val token: String) {

    @Provides
    @OauthScope
    fun provideOauthInterceptor() = OauthInterceptor(token)
}