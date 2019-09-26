package me.scraplesh.module.data.datasources

import io.reactivex.Single
import me.scraplesh.module.data.BuildConfig
import me.scraplesh.module.data.entities.MovieData
import me.scraplesh.module.data.entities.SearchMoviesEnvelope
import me.scraplesh.module.domain.common.ImdbType
import retrofit2.http.GET

typealias ImdbId = String

interface ImdbWebApi {
  @GET("?apikey={imdbApiKey}&r=json&s={query}")
  fun searchMovies(query: String = "warrior"): Single<SearchMoviesEnvelope>

  @GET("?apikey=${BuildConfig.imdbApiKey}&type={type}&r=json&i={imdbId}")
  fun getMovie(type: ImdbType, imdbId: ImdbId): Single<MovieData>
}
