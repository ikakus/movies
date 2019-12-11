package me.scraplesh.movies.data.datasources

import io.reactivex.Observable
import me.scraplesh.movies.data.BuildConfig
import me.scraplesh.movies.data.entities.web.MovieWebEntity
import me.scraplesh.movies.data.entities.web.SearchMoviesEnvelopeWebEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ImdbWebApi {
  @GET("?apikey=${BuildConfig.imdbApiKey}&r=json&type=movie")
  fun searchMovies(@Query("s") query: String): Observable<SearchMoviesEnvelopeWebEntity>

  @GET("?apikey=${BuildConfig.imdbApiKey}&type=movie&r=json")
  fun getMovie(@Query("i") imdbId: String): Observable<MovieWebEntity>
}
