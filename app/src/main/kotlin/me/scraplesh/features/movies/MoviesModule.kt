package me.scraplesh.features.movies

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import me.scraplesh.movies.domain.usecases.SearchMoviesUseCase
import me.scraplesh.features.movies.BuildConfig
import me.scraplesh.features.movies.MoviesAdapter
import me.scraplesh.features.movies.MoviesBindings
import me.scraplesh.features.movies.MoviesFeature
import me.scraplesh.features.movies.MoviesFragment
import me.scraplesh.features.movies.MoviesView
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moviesModule = module {
  scope(named<MoviesFragment>()) {
    scoped<AndroidBindings<MoviesView>> { (lifecycleOwner: LifecycleOwner) ->
      MoviesBindings(
          lifecycleOwner,
          MoviesFeature(
              MoviesFeature.State(defaultQuery = BuildConfig.defaultQuery),
              SearchMoviesUseCase(get())
          ),
          get()
      )
    }
    scoped { MoviesView(MoviesAdapter()) }
  }
}
