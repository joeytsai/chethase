package com.googlejobapp.chethase.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

/**
 * DAO for the Post entity
 */
@Dao
abstract class PostDao : BaseDao<Post> {
    @Query("SELECT * FROM post")
    abstract fun findAll(): LiveData<List<Post>>
}