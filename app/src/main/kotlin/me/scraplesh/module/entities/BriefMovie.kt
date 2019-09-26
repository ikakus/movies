package me.scraplesh.module.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import me.scraplesh.module.domain.common.ImdbType
import me.scraplesh.module.domain.entities.BriefMovieEntity

@Parcelize
data class BriefMovie(
  val title: String,
  val year: String,
  val imdbId: String,
  val type: ImdbType,
  val posterUrl: String
) :
  Parcelable,
  Mapping<BriefMovieEntity> {

  constructor(movie: BriefMovieEntity) : this(
    title = movie.title,
    year = movie.year,
    imdbId = movie.imdbId,
    type = movie.type,
    posterUrl = movie.posterUrl
  )

  override val entity: BriefMovieEntity
    get() = BriefMovieEntity(title, year, imdbId, type, posterUrl)

}
