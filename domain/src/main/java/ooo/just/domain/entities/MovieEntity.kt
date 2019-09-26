package ooo.just.domain.entities

import ooo.just.domain.common.ImdbType
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
  val writer: String,
  val actors: String,
  val plot: String,
  val language: String,
  val country: String,
  val awards: String,
  val poster: String,
  val ratings: List<RatingEntity>,
  val dvd: String,
  val boxOffice: String,
  val production: String,
  val website: String
) : Entity
