package me.scraplesh.module.domain.repo

import io.reactivex.Single
import me.scraplesh.module.domain.entities.BriefMovieEntity
import me.scraplesh.module.domain.entities.MovieEntity

interface MoviesRepository {
  fun searchMovies(query: String): Single<List<BriefMovieEntity>>
  fun getMovie(imdbId: String): Single<MovieEntity>
}