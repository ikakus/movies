package me.scraplesh.movies.domain.repo

import io.reactivex.Single
import me.scraplesh.movies.domain.entities.BriefMovieEntity
import me.scraplesh.movies.domain.entities.MovieEntity

interface MoviesRepository {
  fun searchMovies(query: String): Single<List<BriefMovieEntity>>
  fun getMovie(imdbId: String): Single<MovieEntity>
}