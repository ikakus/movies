package me.scraplesh.module.domain.entities

data class MovieEntity(
  val title: String,
  val year: String,
  val imdbId: String,
  val rated: String,
  val released: String,
  val runtime: String,
  val genre: String,
  val director: String,
  val plot: String,
  val language: String,
  val country: String,
  val poster: String,
  val rating: String
) : Entity
