package me.scraplesh.module.core.di

import dagger.Component
import me.scraplesh.module.domain.repo.MoviesRepository

@Component(modules = [DomainModule::class])
interface DomainComponent {
  fun moviesRepository(): MoviesRepository
}