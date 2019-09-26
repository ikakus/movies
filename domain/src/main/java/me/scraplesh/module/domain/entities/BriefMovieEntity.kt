package me.scraplesh.module.domain.entities

import me.scraplesh.module.domain.common.ImdbType

data class BriefMovieEntity(
  val title: String,
  val year: String,
  val imdbId: String,
  val type: ImdbType,
  val posterUrl: String
) : Entity
