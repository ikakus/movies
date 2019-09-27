package me.scraplesh.module.features.movies

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import me.scraplesh.module.features.movies.MoviesFeature.Wish
import me.scraplesh.module.features.movies.MoviesView.UiEvent

class MoviesBindings(lifecycleOwner: LifecycleOwner, private val feature: MoviesFeature) :
  AndroidBindings<MoviesView>(lifecycleOwner) {

  override fun setup(view: MoviesView) {
    binder.bind(feature to view using { state ->
      MoviesView.ViewModel(
        movies = state.movies,
        isLoading = state.isLoading,
        error = state.error
      )
    })
    binder.bind(view to feature using { uiEvent ->
      when (uiEvent) {
        UiEvent.Retry -> Wish.RetryLoadingMovies
        is UiEvent.MovieSelected -> Wish.ShowMovie(uiEvent.movie)
      }
    })
  }
}