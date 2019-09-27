package me.scraplesh.module.features.movie.di

import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides
import me.scraplesh.module.domain.entities.BriefMovieEntity
import me.scraplesh.module.domain.repo.MoviesRepository
import me.scraplesh.module.domain.usecases.GetMovieUseCase
import me.scraplesh.module.features.movie.MovieBindings
import me.scraplesh.module.features.movie.MovieFeature
import me.scraplesh.module.navigation.Coordinator

@Module
class MovieModule(
  private val movie: BriefMovieEntity,
  private val lifecycleOwner: LifecycleOwner,
  private val coordinator: Coordinator
) {
  @Provides
  @MovieScope
  fun feature(moviesRepository: MoviesRepository): MovieFeature = MovieFeature(
    MovieFeature.State(imdbId = movie.imdbId),
    GetMovieUseCase(moviesRepository)
  )

  @Provides
  @MovieScope
  fun bindings(feature: MovieFeature): MovieBindings = MovieBindings(
    lifecycleOwner,
    feature,
    coordinator
  )
}
