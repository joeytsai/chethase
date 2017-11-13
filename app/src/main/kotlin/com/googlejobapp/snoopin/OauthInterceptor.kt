package com.googlejobapp.snoopin

import okhttp3.Interceptor
import okhttp3.Response

class OauthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
            chain.request().newBuilder()
                    .header(AUTHORIZATION_HEADER, "bearer $token")
                    .build()
    )
}