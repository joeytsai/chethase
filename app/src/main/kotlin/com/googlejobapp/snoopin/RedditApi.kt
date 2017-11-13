package com.googlejobapp.snoopin

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Authenticate to call the Reddit API
 *
 * https://github.com/reddit/reddit/wiki/OAuth2
 * https://viksaaskool.wordpress.com/2015/08/30/how-to-do-userless-reddit-oauth-2-on-android-with-retrofit-tutorial/
 *
 */
interface RedditApi {
    @FormUrlEncoded
    @POST("/api/v1/access_token")
    fun accessToken(
            @Field("device_id") device: String,
            @Field("grant_type") grant: String = "https://oauth.reddit.com/grants/installed_client"
    ): Single<AccessTokenRes>
}

data class AccessTokenRes(
        val access_token: String,
        val token_type: String,
        val expires_in: Long,
        val scope: String
)