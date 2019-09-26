package ooo.just.data.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ooo.just.data.Converters
import ooo.just.data.entities.database.MovieDbEntity
import ooo.just.data.entities.database.MoviesDao

@Database(
    entities = [MovieDbEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ModulDatabase : RoomDatabase() {
  abstract fun movieDao(): MoviesDao
}
