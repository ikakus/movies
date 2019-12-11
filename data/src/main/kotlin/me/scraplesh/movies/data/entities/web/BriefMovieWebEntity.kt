package me.scraplesh.movies.data.entities.web

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.scraplesh.movies.domain.entities.Mapping
import me.scraplesh.movies.domain.entities.MovieEntity

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
data class BriefMovieWebEntity(
  @Json(name = TITLE) val title: String,
  @Json(name = YEAR) val year: String,
  @Json(name = TYPE) val type: String,
  @Json(name = IMDB_ID) val imdbId: String,
  @Json(name = POSTER) val posterUrl: String
) : Mapping<MovieEntity> {

  override val entity: MovieEntity
    get() = MovieEntity(
      title = title,
      year = year,
      type = type,
      imdbId = imdbId,
      posterUrl = posterUrl
    )

  private companion object {
    const val TITLE = "Title"
    const val YEAR = "Year"
    const val TYPE = "Type"
    const val IMDB_ID = "imdbID"
    const val POSTER = "Poster"
  }
}
