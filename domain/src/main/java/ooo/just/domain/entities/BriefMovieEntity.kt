package ooo.just.domain.entities

import ooo.just.domain.common.ImdbType

data class BriefMovieEntity(
  val title: String,
  val year: Int,
  val imdbId: String,
  val type: ImdbType,
  val posterUrl: String
) : Entity
