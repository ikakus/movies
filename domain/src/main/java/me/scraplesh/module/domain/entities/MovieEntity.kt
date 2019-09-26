package me.scraplesh.module.domain.entities

import me.scraplesh.module.domain.common.ImdbType
import java.util.*

data class MovieEntity(
  val title: String,
  val year: Int,
  val imdbId: String,
  val type: ImdbType,
  val rated: String,
  val released: Date,
  val runtime: String,
  val genre: String,
  val director: String,
  val plot: String,
  val language: String,
  val country: String,
  val poster: String,
  val rating: String
) : Entity
