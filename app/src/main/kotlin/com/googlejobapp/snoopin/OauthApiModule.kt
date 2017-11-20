package com.googlejobapp.snoopin

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Dagger 2 Module to provide OauthApi Retrofit service
 */
@Module
class OauthApiModule {

    @Provides
    @OauthScope
    fun provideOauthApi(okHttpClient: OkHttpClient, oauthInterceptor: OauthInterceptor): OauthApi {

        val oauthClient = okHttpClient.newBuilder()
                .addInterceptor(oauthInterceptor)
                .build()

        val oauthRetrofit = Retrofit.Builder()
                .baseUrl(OAUTH_BASE_URL)
                .client(oauthClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        return oauthRetrofit.create(OauthApi::class.java)
    }

    companion object {
        val OAUTH_BASE_URL = "https://oauth.reddit.com"
    }
}
