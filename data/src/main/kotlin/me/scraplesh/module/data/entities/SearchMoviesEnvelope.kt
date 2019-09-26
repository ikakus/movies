package me.scraplesh.module.data.entities

import com.squareup.moshi.Json

data class SearchMoviesEnvelope(
  @Json(name = SEARCH) val results: List<BriefMovieData> = emptyList()
  ) {

  private companion object {
    const val SEARCH = "Search"
  }
}