package me.scraplesh.module.features.movies.di

import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides
import me.scraplesh.module.BuildConfig
import me.scraplesh.module.domain.repo.MoviesRepository
import me.scraplesh.module.domain.usecases.SearchMoviesUseCase
import me.scraplesh.module.features.movies.MoviesBindings
import me.scraplesh.module.features.movies.MoviesFeature

@Module
class MoviesModule(private val lifecycleOwner: LifecycleOwner) {
  @Provides
  @MoviesScope
  fun feature(moviesRepository: MoviesRepository): MoviesFeature = MoviesFeature(
    MoviesFeature.State(defaultQuery = BuildConfig.defaultQuery),
    SearchMoviesUseCase(moviesRepository)
  )

  @Provides
  @MoviesScope
  fun bindings(feature: MoviesFeature): MoviesBindings = MoviesBindings(lifecycleOwner, feature)
}
