package me.scraplesh.movies.di

import me.scraplesh.movies.data.repo.RemoteMoviesRepository
import me.scraplesh.movies.domain.repo.MoviesRepository
import org.koin.dsl.module

val domainModule = module {
  factory<MoviesRepository> { RemoteMoviesRepository(get()) }
}