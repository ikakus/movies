package me.scraplesh.movies.domain.entities

data class MovieEntity(
  val title: String,
  val year: String,
  val imdbId: String,
  val posterUrl: String,
  val type: String? = null,
  val rated: String? = null,
  val released: String? = null,
  val runtime: String? = null,
  val genre: String? = null,
  val director: String? = null,
  val plot: String? = null,
  val language: String? = null,
  val country: String? = null,
  val rating: String? = null
) : Entity
