package com.googlejobapp.snoopin

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Scope

/**
 * Created by joey on 11/12/17.
 */
@Module
class OauthRedditApiModule {
    @Provides
    @OauthScope
    fun provideOauthRedditApi(okHttpClient: OkHttpClient, oauthInterceptor: OauthInterceptor): OauthRedditApi {

        val oauthClient = okHttpClient.newBuilder()
                .addInterceptor(oauthInterceptor)
                .build()

        val oauthRetrofit = Retrofit.Builder()
                .baseUrl(OAUTH_BASE_URL)
                .client(oauthClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        return oauthRetrofit.create(OauthRedditApi::class.java)
    }

    companion object {
        val OAUTH_BASE_URL = "https://oauth.reddit.com"
    }
}

@Scope
annotation class OauthScope