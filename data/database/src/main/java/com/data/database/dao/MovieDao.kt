package com.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.data.database.entity.FavoriteMovieEntity
import com.data.database.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieList: List<MovieEntity>)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Transaction
    suspend fun deleteMovieList(movieList: List<MovieEntity>) {
        for (movie in movieList) {
            deleteMovie(movie)
        }
    }

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): MovieEntity

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovies(): List<FavoriteMovieEntity>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    fun getFavoriteMovieById(movieId: Int): FavoriteMovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @Delete
    suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @Transaction
    suspend fun addFavoriteMoviesToMovieList() {
        val favoriteMovieList = getAllFavoriteMovies()
        val movieList = getAllMovies()

        outer@ for (favoriteMovie in favoriteMovieList) {
            for (movie in movieList) {
                if (favoriteMovie.movieId == movie.id) {
                    updateMovie(movie.copy(isFavorite = true))
                    break@outer
                }
            }
        }
    }

    @Transaction
    suspend fun addToFavorite(movieId: Int) {
        val favoriteMovie = getFavoriteMovieById(movieId)
        if (favoriteMovie == null) {
            insertFavoriteMovie(FavoriteMovieEntity(movieId = movieId))
            updateMovie(getMovieById(movieId).copy(isFavorite = true))
        }
    }

    @Transaction
    suspend fun removeFromFavorite(movieId: Int) {
        val favoriteMovie = getFavoriteMovieById(movieId)
        if (favoriteMovie != null) {
            deleteFavoriteMovie(favoriteMovie)
        }

        updateMovie(getMovieById(movieId).copy(isFavorite = false))
    }
}