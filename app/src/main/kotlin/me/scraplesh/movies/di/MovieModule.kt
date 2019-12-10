package me.scraplesh.movies.di

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import me.scraplesh.movies.domain.entities.BriefMovieEntity
import me.scraplesh.movies.domain.usecases.GetMovieUseCase
import me.scraplesh.movies.features.movie.MovieBindings
import me.scraplesh.movies.features.movie.MovieFeature
import me.scraplesh.movies.features.movie.MovieFragment
import me.scraplesh.movies.features.movie.MovieView
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieModule = module {
  scope(named<MovieFragment>()) {
    scoped<AndroidBindings<MovieView>> { (movie: BriefMovieEntity, lifecycleOwner: LifecycleOwner) ->
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