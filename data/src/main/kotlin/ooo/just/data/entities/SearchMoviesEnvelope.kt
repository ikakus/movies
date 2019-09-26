package ooo.just.data.entities

import com.squareup.moshi.Json

data class SearchMoviesEnvelope(@Json(name = SEARCH) val search: List<BriefMovieData>) {
  private companion object {
    const val SEARCH = "Search"
  }
}