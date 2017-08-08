package com.googlejobapp.snoopin

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by joey on 8/8/17.
 */
class RedditApiTest {

    private fun oauthRedditApi(baseUrl: HttpUrl): OauthRedditApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(OauthRedditApi::class.java)
    }

    fun file2string(file: String): String {
        val inputStream = javaClass.classLoader.getResourceAsStream(file)
        return inputStream.bufferedReader().use { it.readText() }
    }

    @Test
    fun popularSubreddits_test01() {
        val body = file2string("popularSubreddits-01.json")
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody(body))
        server.start()
        val baseUrl = server.url("baseUrl/")
        val api = oauthRedditApi(baseUrl)
        val subreddits = api.popularSubreddits().blockingGet()

        Assert.assertEquals("elements returned", 5, subreddits.data.children.size)
        Assert.assertEquals("first element", "AskReddit", subreddits.data.children.first().data.display_name)
        Assert.assertEquals("first subscribers", 17723258, subreddits.data.children.first().data.subscribers)
        Assert.assertEquals("last element", "funny", subreddits.data.children.last().data.display_name)
        Assert.assertEquals("last subscribers", 17777207, subreddits.data.children.last().data.subscribers)

        server.shutdown()
    }

    @Test
    fun popularSubreddits_test02() {
        val body = file2string("popularSubreddits-02.json")
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody(body))
        server.start()
        val baseUrl = server.url("baseUrl/")
        val api = oauthRedditApi(baseUrl)
        val subreddits = api.popularSubreddits().blockingGet()

        Assert.assertEquals("elements returned", 5, subreddits.data.children.size)
        Assert.assertEquals("first element", "AskReddit", subreddits.data.children.first().data.display_name)
        Assert.assertEquals("first element subscribers", 17724833, subreddits.data.children.first().data.subscribers)
        Assert.assertEquals("last element", "funny", subreddits.data.children.last().data.display_name)
        Assert.assertEquals("last subscribers", 17779487, subreddits.data.children.last().data.subscribers)

        server.shutdown()
    }
}