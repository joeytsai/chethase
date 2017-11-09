package com.googlejobapp.snoopin

import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.UUID


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


interface OauthRedditApi {
    @GET("/subreddits/popular")
    fun popularSubreddits(@Query("limit") limit: Int = 5): Single<SubredditListingRes>

    @GET("/r/pics/hot")
    fun hotPics(@Query("limit") limit: Int = 5): Single<PostListingRes>

    @GET("/r/androiddev/hot")
    fun hotAndroiddev(@Query("limit") limit: Int = 5): Single<PostListingRes>
}

data class SubredditListingRes(
        val kind: String,
        val data: SubredditListingDataRes
)

data class SubredditListingDataRes(
        val modhash: String,
        val before: String,
        val after: String,
        val children: List<SubredditThingRes>
)

data class SubredditThingRes(
        val kind: String,
        val data: SubredditThingDataRes
)

data class SubredditThingDataRes(
        val display_name: String,
        val title: String,
        val public_description: String,
        val subscribers: Int
)

data class PostListingRes(
        val kind: String,
        val data: PostListingDataRes
)

data class PostListingDataRes(
        val modhash: String,
        val before: String,
        val after: String,
        val children: List<PostThingRes>
)

data class PostThingRes(
        val kind: String,
        val data: PostThingDataRes
)

data class PostThingDataRes(
        val title: String,
        val permalink: String,
        val url: String,
        val thumbnail: String,
        val preview: PreviewRes
)

data class PreviewRes(
        val images: List<PreviewImageRes>,
        val enabled: Boolean
)

data class PreviewImageRes(
        val id: String,
        val source: SourceRes,
        val resolutions: List<SourceRes>
)

data class SourceRes(
        val url: String,
        val width: Int,
        val height: Int
)


const val USER_AGENT = "android:com.googlejobapp.chethase:v0.0.1 (by /u/thechickenbane)"

fun deviceId() = UUID.randomUUID().toString()


const val OAUTH_BASE_URL = "https://oauth.reddit.com"

class OauthInt(val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(
            chain.request().newBuilder()
                    .header(AuthorizationHeader, "bearer $token")
                    .build()
    )
}

