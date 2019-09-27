package me.scraplesh.movies.domain.entities

data class BriefMovieEntity(
  val title: String,
  val year: String,
  val type: String,
  val imdbId: String,
  val posterUrl: String
) : Entity
