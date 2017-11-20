package com.googlejobapp.chethase.room

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger 2 module to provide the RoomDatabase
 */
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): AppDatabase = Room
            .databaseBuilder(context, AppDatabase::class.java, "room-database")
            .fallbackToDestructiveMigration()
            .build()
}