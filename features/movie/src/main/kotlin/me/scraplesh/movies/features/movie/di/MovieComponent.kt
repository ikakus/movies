package me.scraplesh.movies.features.movie.di

import dagger.Component
import me.scraplesh.movies.core.di.DomainComponent
import me.scraplesh.movies.features.movie.MovieFragment

@MovieScope
@Component(modules = [MovieModule::class], dependencies = [DomainComponent::class])
interface MovieComponent {
  fun inject(fragment: MovieFragment)
}