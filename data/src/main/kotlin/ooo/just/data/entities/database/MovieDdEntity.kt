package ooo.just.data.entities.database

import androidx.room.*
import io.reactivex.Single
import ooo.just.data.entities.MappingData
import ooo.just.domain.entities.MovieEntity

@Entity(tableName = "movies")
data class MovieDbEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0
) : MappingData<MovieEntity> {
    override val entity: MovieEntity get() = MovieEntity()
}

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieDbEntity)

    @Query("SELECT * FROM movies")
    fun getMovies(): Single<List<MovieDbEntity>>
}