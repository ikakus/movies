package me.scraplesh.movies.data.repo

import io.reactivex.Single
import me.scraplesh.movies.data.datasources.ImdbWebApi
import me.scraplesh.movies.domain.entities.BriefMovieEntity
import me.scraplesh.movies.domain.entities.MovieEntity
import me.scraplesh.movies.domain.repo.MoviesRepository

class RemoteMoviesRepository(private val webDataSource: ImdbWebApi) : MoviesRepository {
  override fun searchMovies(query: String): Single<List<BriefMovieEntity>> =
    webDataSource.searchMovies(query)
      .map { envelope -> envelope.results.map { it.entity } }

  override fun getMovie(imdbId: String): Single<MovieEntity> =
    webDataSource.getMovie(imdbId).map { it.entity }
}