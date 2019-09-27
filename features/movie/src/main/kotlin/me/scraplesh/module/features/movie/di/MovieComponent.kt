package me.scraplesh.module.features.movie.di

import dagger.Component
import me.scraplesh.module.core.di.DomainComponent
import me.scraplesh.module.features.movie.MovieFragment

@MovieScope
@Component(modules = [MovieModule::class], dependencies = [DomainComponent::class])
interface MovieComponent {
  fun inject(fragment: MovieFragment)
}