package me.scraplesh.module.features.movies.di

import dagger.Component
import me.scraplesh.module.core.di.DomainComponent
import me.scraplesh.module.features.movie.di.MovieScope
import me.scraplesh.module.features.movies.MoviesFragment

@MovieScope
@Component(modules = [MoviesModule::class], dependencies = [DomainComponent::class])
interface MoviesComponent {
  fun inject(fragment: MoviesFragment)
}