package me.scraplesh.movies.core.di

import dagger.Component
import me.scraplesh.movies.domain.repo.MoviesRepository

@Component(modules = [DomainModule::class])
interface DomainComponent {
  fun moviesRepository(): MoviesRepository
}