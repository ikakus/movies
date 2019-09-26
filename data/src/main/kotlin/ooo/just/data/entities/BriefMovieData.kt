package ooo.just.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ooo.just.domain.common.ImdbType
import ooo.just.domain.entities.BriefMovieEntity

/*
{
    "Title": "Warrior",
    "Year": "2011",
    "imdbID": "tt1291584",
    "Type": "movie",
    "Poster": "https://m.media-amazon.com/images/M/MV5BMTk4ODk5MTMyNV5BMl5BanBnXkFtZTcwMDMyNTg0Ng@@._V1_SX300.jpg"
}
 */
@JsonClass(generateAdapter = true)
data class BriefMovieData(
  @Json(name = TITLE) val title: String,
  @Json(name = YEAR) val year: Int,
  @Json(name = IMDB_ID) val imdbId: String,
  @Json(name = TYPE) val type: ImdbType,
  @Json(name = POSTER) val posterUrl: String
) : MappingData<BriefMovieEntity> {

  override val entity: BriefMovieEntity
    get() = BriefMovieEntity(
      title,
      year,
      imdbId,
      type,
      posterUrl
    )

  private companion object {
    const val TITLE = "Title"
    const val YEAR = "Year"
    const val IMDB_ID = "imdbID"
    const val TYPE = "Type"
    const val POSTER = "Poster"
  }
}
