package com.googlejobapp.snoopin

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.UUID


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



