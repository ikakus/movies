package me.scraplesh.movies.core.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.scraplesh.movies.domain.entities.MovieEntity
import me.scraplesh.movies.domain.entities.Mapping

@Parcelize
data class Movie(
  val title: String,
  val year: String,
  val type: String?,
  val imdbId: String,
  val posterUrl: String
) :
  Parcelable,
  Mapping<MovieEntity> {

  constructor(movie: MovieEntity) : this(
    title = movie.title,
    year = movie.year,
    type = movie.type,
    imdbId = movie.imdbId,
    posterUrl = movie.posterUrl
  )

  override val entity: MovieEntity
    get() = MovieEntity(
      title = title,
      year = year,
      type = type,
      imdbId = imdbId,
      posterUrl = posterUrl
    )

}
