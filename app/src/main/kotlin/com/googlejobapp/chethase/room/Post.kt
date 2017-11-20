package com.googlejobapp.chethase.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * This entity, persisted by Room, represents one post in a subreddit
 */
@Entity
data class Post(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val title: String,
        val permalink: String,
        val url: String,
        val thumbnail: String
)