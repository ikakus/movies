package me.scraplesh.movies.features.movie.di

import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides
import me.scraplesh.movies.domain.entities.BriefMovieEntity
import me.scraplesh.movies.domain.repo.MoviesRepository
import me.scraplesh.movies.domain.usecases.GetMovieUseCase
import me.scraplesh.movies.features.movie.MovieBindings
import me.scraplesh.movies.features.movie.MovieFeature
import me.scraplesh.movies.navigation.Coordinator

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
