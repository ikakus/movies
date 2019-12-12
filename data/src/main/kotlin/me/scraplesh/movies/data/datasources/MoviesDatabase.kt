package me.scraplesh.movies.data.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import me.scraplesh.movies.data.entities.database.MovieDbEntity
import me.scraplesh.movies.data.entities.database.MoviesDao

@Database(entities = [MovieDbEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
  abstract fun moviesDao(): MoviesDao
}
