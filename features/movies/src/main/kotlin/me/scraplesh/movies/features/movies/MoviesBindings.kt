package me.scraplesh.movies.features.movies

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import me.scraplesh.movies.features.movies.MoviesFeature.Wish
import me.scraplesh.movies.features.movies.MoviesView.UiEvent
import me.scraplesh.movies.navigation.Coordinator
import me.scraplesh.movies.navigation.NavigationEvent

class MoviesBindings(
  lifecycleOwner: LifecycleOwner,
  private val feature: MoviesFeature,
  coordinator: Coordinator
) :
  AndroidBindings<MoviesView>(lifecycleOwner) {

  init {
    binder.bind(feature.news to coordinator using { news -> (news as? NavigationEvent) })
  }

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