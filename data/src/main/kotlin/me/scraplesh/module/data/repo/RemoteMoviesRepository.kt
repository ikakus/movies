package me.scraplesh.module.data.repo

import io.reactivex.Single
import me.scraplesh.module.data.datasources.ImdbWebApi
import me.scraplesh.module.domain.entities.BriefMovieEntity
import me.scraplesh.module.domain.repo.MoviesRepository

class RemoteMoviesRepository(private val webDataSource: ImdbWebApi) : MoviesRepository {
  override fun searchMovies(query: String): Single<List<BriefMovieEntity>> =
    webDataSource.searchMovies(query)
      .map { envelope -> envelope.results.map { it.entity } }
}