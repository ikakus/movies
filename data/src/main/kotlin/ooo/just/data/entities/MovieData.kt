package ooo.just.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ooo.just.domain.common.ImdbType
import ooo.just.domain.entities.MovieEntity
import java.util.*

/*
{
    "Title": "Mad Max 2: The Road Warrior",
    "Year": "1981",
    "Rated": "R",
    "Released": "21 May 1982",
    "Runtime": "94 min",
    "Genre": "Action, Adventure, Sci-Fi, Thriller",
    "Director": "George Miller",
    "Writer": "Terry Hayes (screenplay by), George Miller (screenplay by), Brian Hannant (screenplay with)",
    "Actors": "Mel Gibson, Bruce Spence, Michael Preston, Max Phipps",
    "Plot": "In the post-apocalyptic Australian wasteland, a cynical drifter agrees to help a small, gasoline rich community escape a horde of bandits.",
    "Language": "English",
    "Country": "Australia",
    "Awards": "8 wins & 10 nominations.",
    "Poster": "https://m.media-amazon.com/images/M/MV5BN2VlNjNhZWQtMTY2OC00Y2E1LWJkNGUtMDU4M2ViNzliMGYwXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg",
    "Ratings": [
        {
            "Source": "Internet Movie Database",
            "Value": "7.6/10"
        },
        {
            "Source": "Rotten Tomatoes",
            "Value": "95%"
        },
        {
            "Source": "Metacritic",
            "Value": "77/100"
        }
    ],
    "Metascore": "77",
    "imdbRating": "7.6",
    "imdbVotes": "155,980",
    "imdbID": "tt0082694",
    "Type": "movie",
    "DVD": "25 Sep 1997",
    "BoxOffice": "N/A",
    "Production": "Warner Bros. Pictures",
    "Website": "N/A",
    "Response": "True"
}
 */
@JsonClass(generateAdapter = true)
data class MovieData(
  @Json(name = TITLE) val title: String,
  @Json(name = YEAR) val year: Int,
  @Json(name = RATED) val imdbId: String,
  @Json(name = RELEASED) val type: ImdbType,
  @Json(name = RUNTIME) val rated: String,
  @Json(name = GENRE) val released: Date,
  @Json(name = DIRECTOR) val runtime: String,
  @Json(name = WRITER) val genre: String,
  @Json(name = ACTORS) val director: String,
  @Json(name = PLOT) val writer: String,
  @Json(name = LANGUAGE) val actors: String,
  @Json(name = COUNTRY) val plot: String,
  @Json(name = AWARDS) val language: String,
  @Json(name = POSTER) val country: String,
  @Json(name = RATINGS) val awards: String,
  @Json(name = IMDBID) val poster: String,
  @Json(name = TYPE) val ratings: List<RatingData>,
  @Json(name = DVD) val dvd: String,
  @Json(name = BOXOFFICE) val boxOffice: String,
  @Json(name = PRODUCTION) val production: String,
  @Json(name = WEBSITE) val website: String
) : MappingData<MovieEntity> {

  override val entity: MovieEntity
    get() = MovieEntity(
      title = title,
      year = year,
      imdbId = imdbId,
      type = type,
      rated = rated,
      released = released,
      runtime = runtime,
      genre = genre,
      director = director,
      writer = writer,
      actors = actors,
      plot = plot,
      language = language,
      country = country,
      awards = awards,
      poster = poster,
      ratings = ratings.map { it.entity },
      dvd = dvd,
      boxOffice = boxOffice,
      production = production,
      website = website
    )

  private companion object {
    const val TITLE = "Title"
    const val YEAR = "Year"
    const val RATED = "Rated"
    const val RELEASED = "Released"
    const val RUNTIME = "Runtime"
    const val GENRE = "Genre"
    const val DIRECTOR = "Director"
    const val WRITER = "Writer"
    const val ACTORS = "Actors"
    const val PLOT = "Plot"
    const val LANGUAGE = "Language"
    const val COUNTRY = "Country"
    const val AWARDS = "Awards"
    const val POSTER = "Poster"
    const val RATINGS = "Ratings"
    const val IMDBID = "imdbID"
    const val TYPE = "Type"
    const val DVD = "DVD"
    const val BOXOFFICE = "BoxOffice"
    const val PRODUCTION = "Production"
    const val WEBSITE = "Website"
  }

}
