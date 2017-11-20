package com.googlejobapp.chethase.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * RoomDatabase configuration
 */
@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}