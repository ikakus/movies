package ooo.just.data.entities.database

import androidx.room.*
import io.reactivex.Single
import ooo.just.data.entities.MappingData
import ooo.just.domain.entities.BriefMovieEntity

@Entity(tableName = "movies")
data class MovieDbEntity(
    @PrimaryKey var id: String
)

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieDbEntity)

    @Query("SELECT * FROM movies")
    fun getMovies(): Single<List<MovieDbEntity>>
}