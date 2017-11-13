package com.googlejobapp.snoopin

/**
 * Created by joey on 8/8/17.
 * TODO remove this file
 */

//const val BASE_URL = "https://www.reddit.com"
//
//val retrofit: Retrofit by lazy {
//    Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .build()
//}
//
//val redditApi: RedditApi by lazy {
//    retrofit.create(RedditApi::class.java)
//}

//
//fun createOauthRedditApi(token: String): OauthRedditApi {
//
//    val oauthClient = okHttpClient.newBuilder()
//            .addInterceptor(OauthInt(token))
//            .build()
//
//    val oauthRetrofit = Retrofit.Builder()
//            .baseUrl(OAUTH_BASE_URL)
//            .client(oauthClient)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//            .build()
//    return oauthRetrofit.create(OauthRedditApi::class.java)
//}
//
