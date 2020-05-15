package me.scraplesh.features.movie

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import me.scraplesh.movies.domain.entities.MovieEntity
import me.scraplesh.movies.domain.usecases.GetMovieUseCase
import me.scraplesh.features.movie.MovieBindings
import me.scraplesh.features.movie.MovieFeature
import me.scraplesh.features.movie.MovieFragment
import me.scraplesh.features.movie.MovieView
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {
  scope(named<MovieFragment>()) {
    scoped<AndroidBindings<MovieView>> { (movie: MovieEntity, lifecycleOwner: LifecycleOwner) ->
      MovieBindings(
          lifecycleOwner,
          MovieFeature(
              MovieFeature.State(imdbId = movie.imdbId),
              GetMovieUseCase(get())
          ),
          get()
      )
    }
    scoped { MovieView() }
  }
}
