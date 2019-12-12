package me.scraplesh.movies.data.entities.web

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchMoviesEnvelopeWebEntity(@Json(name = SEARCH) val results: List<BriefMovieWebEntity>) {
  private companion object {
    const val SEARCH = "Search"
  }
}