package me.scraplesh.movies.features.movies.di

import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides
import me.scraplesh.movies.domain.repo.MoviesRepository
import me.scraplesh.movies.domain.usecases.SearchMoviesUseCase
import me.scraplesh.movies.features.movies.BuildConfig
import me.scraplesh.movies.features.movies.MoviesBindings
import me.scraplesh.movies.features.movies.MoviesFeature
import me.scraplesh.movies.navigation.Coordinator

@Module
class MoviesModule(
  private val lifecycleOwner: LifecycleOwner,
  private val coordinator: Coordinator
) {
  @Provides
  @MoviesScope
  fun feature(moviesRepository: MoviesRepository): MoviesFeature = MoviesFeature(
    MoviesFeature.State(defaultQuery = BuildConfig.defaultQuery),
    SearchMoviesUseCase(moviesRepository)
  )

  @Provides
  @MoviesScope
  fun bindings(feature: MoviesFeature): MoviesBindings = MoviesBindings(
    lifecycleOwner,
    feature,
    coordinator
  )
}
