package me.scraplesh.module.features.movies.di

import dagger.Component
import me.scraplesh.module.core.DomainComponent
import me.scraplesh.module.features.movies.MoviesFragment

@MoviesScope
@Component(modules = [MoviesModule::class], dependencies = [DomainComponent::class])
interface MoviesComponent {
  fun inject(fragment: MoviesFragment)
}