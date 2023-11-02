package com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.database.dao.MovieDao
import com.data.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract val dao: MovieDao
}