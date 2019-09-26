package me.scraplesh.module.data.datasources

import io.reactivex.Single
import me.scraplesh.module.data.BuildConfig
import me.scraplesh.module.data.entities.MovieData
import me.scraplesh.module.data.entities.SearchMoviesEnvelope
import retrofit2.http.GET

interface ImdbWebApi {
  @GET("?apikey=${BuildConfig.imdbApiKey}&r=json&s={query}&type=movie")
  fun searchMovies(query: String = "warrior"): Single<SearchMoviesEnvelope>

  @GET("?apikey=${BuildConfig.imdbApiKey}&type=movie&r=json&i={imdbId}")
  fun getMovie(imdbId: String): Single<MovieData>
}
