package me.scraplesh.movies.data.entities.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface MoviesDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovies(movies: List<MovieDbEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun updateMovie(movie: MovieDbEntity)

  @Query("SELECT * FROM movies ORDER BY imdbId ASC")
  fun getAllMovies(): Single<List<MovieDbEntity>>

  @Query("SELECT * FROM movies WHERE imdbId = :imdbId")
  fun getMovie(imdbId: String): Single<MovieDbEntity>
}