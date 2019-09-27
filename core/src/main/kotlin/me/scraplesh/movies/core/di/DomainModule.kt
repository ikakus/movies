package me.scraplesh.movies.core.di

import dagger.Module
import dagger.Provides
import me.scraplesh.movies.data.datasources.ImdbWebApi
import me.scraplesh.movies.data.repo.RemoteMoviesRepository
import me.scraplesh.movies.domain.repo.MoviesRepository

@Module
class DomainModule(private val webDataSource: ImdbWebApi) {
  @Provides
  fun moviesRepository(): MoviesRepository =
    RemoteMoviesRepository(webDataSource)
}
