package me.scraplesh.movies.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.scraplesh.movies.domain.entities.Mapping
import me.scraplesh.movies.domain.entities.MovieEntity

@Entity(tableName = "movies")
data class MovieDbEntity(
  val title: String,
  val year: String,
  @PrimaryKey val imdbId: String,
  val type: String?,
  val posterUrl: String,
  val rated: String? = null,
  val released: String? = null,
  val runtime: String? = null,
  val genre: String? = null,
  val director: String? = null,
  val plot: String? = null,
  val language: String? = null,
  val country: String? = null,
  val rating: String? = null
) : Mapping<MovieEntity> {

  constructor(movieEntity: MovieEntity) : this(
    title = movieEntity.title,
    year = movieEntity.year,
    imdbId = movieEntity.imdbId,
    type = movieEntity.type,
    posterUrl = movieEntity.posterUrl,
    rated = movieEntity.rated,
    released = movieEntity.released,
    runtime = movieEntity.runtime,
    genre = movieEntity.genre,
    director = movieEntity.director,
    plot = movieEntity.plot,
    language = movieEntity.language,
    country = movieEntity.country,
    rating = movieEntity.rating
  )

  override val entity: MovieEntity
    get() = MovieEntity(
      title = title,
      year = year,
      imdbId = imdbId,
      posterUrl = posterUrl,
      type = type,
      rated = rated,
      released = released,
      runtime = runtime,
      genre = genre,
      director = director,
      plot = plot,
      language = language,
      country = country,
      rating = rating
    )
}