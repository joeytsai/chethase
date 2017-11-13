package com.googlejobapp.chethase

import com.googlejobapp.chethase.room.RoomModule
import com.googlejobapp.snoopin.HttpModule
import dagger.Component
import javax.inject.Singleton

/**
 * Root component of the object graph, scoped to entire app lifecycle
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class, SharedPrefsModule::class, HttpModule::class))
interface AppComponent {
    fun sharedPrefs(): SharedPrefs
}