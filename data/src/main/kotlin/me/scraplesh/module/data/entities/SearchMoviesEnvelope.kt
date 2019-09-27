package me.scraplesh.module.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchMoviesEnvelope(@Json(name = SEARCH) val results: List<BriefMovieData>) {
  private companion object {
    const val SEARCH = "Search"
  }
}