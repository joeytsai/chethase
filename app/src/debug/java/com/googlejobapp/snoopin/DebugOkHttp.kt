package com.googlejobapp.snoopin

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by joey on 8/8/17.
 */

val okHttpClient: OkHttpClient by lazy {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    OkHttpClient.Builder()
            .addInterceptor(logging)
            .authenticator(ApplicationOnlyOAuth())
            .build()
}
