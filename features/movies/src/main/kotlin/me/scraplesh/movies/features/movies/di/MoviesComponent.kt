package me.scraplesh.movies.features.movies.di

import dagger.Component
import me.scraplesh.movies.core.di.DomainComponent
import me.scraplesh.movies.features.movies.MoviesFragment

@MoviesScope
@Component(modules = [MoviesModule::class], dependencies = [DomainComponent::class])
interface MoviesComponent {
  fun inject(fragment: MoviesFragment)
}