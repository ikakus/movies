package me.scraplesh.module.domain.common

import com.squareup.moshi.Json

// movie, series, episode
enum class ImdbType {
  @Json(name = MOVIE) Movie,
  @Json(name = SERIES) Series,
  @Json(name = EPISODE) Episode;

  override fun toString() = when (this) {
    Movie -> MOVIE
    Series -> SERIES
    Episode -> EPISODE
  }

  private companion object {
    const val MOVIE = "movie"
    const val SERIES = "series"
    const val EPISODE = "episode"
  }
}