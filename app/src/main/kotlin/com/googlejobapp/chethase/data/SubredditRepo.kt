package com.googlejobapp.chethase.data

import android.arch.lifecycle.LiveData
import com.googlejobapp.chethase.room.AppDatabase
import com.googlejobapp.chethase.room.Post
import com.googlejobapp.snoopin.OauthApi

/**
 * Repo operations for Subreddit
 */
class SubredditRepo(private val oauthApi: OauthApi, appDatabase: AppDatabase) {
    private val postDao by lazy { appDatabase.postDao() }
    fun findPosts(): LiveData<List<Post>> = postDao.findAll()

    fun fetchPosts() {
        /*
        disposables.add(
                oauthApi.hotAndroiddev()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            val list = it.data.children.map { it.data }
                            val titles = list.map { it.title }.joinToString()
                            Timber.d("AndroidDev $titles")
                        }, {
                            throw OnErrorNotImplementedException("ugh", it)
                        })
        )
        */
    }
}