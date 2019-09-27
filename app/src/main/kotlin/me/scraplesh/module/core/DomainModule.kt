package me.scraplesh.module.core

import dagger.Module
import dagger.Provides
import me.scraplesh.module.data.datasources.ImdbWebApi
import me.scraplesh.module.data.repo.RemoteMoviesRepository
import me.scraplesh.module.domain.repo.MoviesRepository

@Module
class DomainModule(private val webDataSource: ImdbWebApi) {
  @Provides
  fun moviesRepository(): MoviesRepository =
    RemoteMoviesRepository(webDataSource)
}
