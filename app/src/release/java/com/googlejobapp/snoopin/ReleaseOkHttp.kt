package com.googlejobapp.snoopin

import okhttp3.OkHttpClient

/**
 * Created by joey on 8/8/17.
 */

val okHttpClient: OkHttpClient by lazy {

    OkHttpClient.Builder()
            .authenticator(ApplicationOnlyOAuth())
            .build()
}
