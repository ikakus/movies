package me.scraplesh.module.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.scraplesh.module.domain.entities.BriefMovieEntity

@Parcelize
data class BriefMovie(
  val title: String,
  val year: String,
  val imdbId: String,
  val posterUrl: String
) :
  Parcelable,
  Mapping<BriefMovieEntity> {

  constructor(movie: BriefMovieEntity) : this(
    title = movie.title,
    year = movie.year,
    imdbId = movie.imdbId,
    posterUrl = movie.posterUrl
  )

  override val entity: BriefMovieEntity
    get() = BriefMovieEntity(title, year, imdbId, posterUrl)

}
