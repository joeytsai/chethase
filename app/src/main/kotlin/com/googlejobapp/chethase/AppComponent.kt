package com.googlejobapp.chethase

import com.googlejobapp.chethase.room.RoomModule
import com.googlejobapp.snoopin.OauthComponent
import com.googlejobapp.snoopin.OauthModule
import com.googlejobapp.snoopin.OkHttpModule
import com.googlejobapp.snoopin.RedditApi
import com.googlejobapp.snoopin.RedditApiModule
import dagger.Component
import javax.inject.Singleton

/**
 * Root component of the object graph, scoped to entire app lifecycle
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class, SharedPrefsModule::class, OkHttpModule::class, RedditApiModule::class))
interface AppComponent {
    fun oauthComponent(module: OauthModule): OauthComponent
    fun sharedPrefs(): SharedPrefs
    fun redditApi(): RedditApi
}