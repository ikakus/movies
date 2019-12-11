package me.scraplesh.movies.domain.repo

import io.reactivex.Observable
import me.scraplesh.movies.domain.entities.MovieEntity

interface MoviesRepository {
  fun searchMovies(query: String): Observable<List<MovieEntity>>
  fun getMovie(imdbId: String): Observable<MovieEntity>
}