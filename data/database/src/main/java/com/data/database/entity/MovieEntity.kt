package com.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val isFavorite: Boolean,
    val imgUrl: String,
    val trailerUrl: String,
    val price: Double,
    val genre: String,
    val description: String
)