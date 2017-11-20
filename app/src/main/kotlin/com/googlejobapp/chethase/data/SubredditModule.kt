package com.googlejobapp.chethase.data

import com.googlejobapp.chethase.room.AppDatabase
import com.googlejobapp.snoopin.OauthApi
import dagger.Module
import dagger.Provides

/**
 * Dagger 2 module to provide the Subreddit Repo
 */
@Module
class SubredditModule {

    @Provides
    fun provideSubredditRepo(oauthApi: OauthApi, appDatabase: AppDatabase) = SubredditRepo(oauthApi, appDatabase)
}