package me.scraplesh.module.features.movie

import androidx.lifecycle.LifecycleOwner
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import me.scraplesh.module.features.movie.MovieFeature.Wish
import me.scraplesh.module.features.movie.MovieView.UiEvent
import me.scraplesh.module.navigation.Coordinator
import me.scraplesh.module.navigation.NavigationEvent

class MovieBindings(
  lifecycleOwner: LifecycleOwner,
  private val feature: MovieFeature,
  coordinator: Coordinator
) :
  AndroidBindings<MovieView>(lifecycleOwner) {

  init {
    binder.bind(feature.news to coordinator using { news -> (news as? NavigationEvent) })
  }

  override fun setup(view: MovieView) {
    binder.bind(feature to view using { state ->
      MovieView.ViewModel(
        title = state.movie?.title.orEmpty(),
        posterUrl = state.movie?.poster.orEmpty(),
        year = state.movie?.year.orEmpty(),
        rating = state.movie?.rating.orEmpty(),
        rated = state.movie?.rated.orEmpty(),
        released = state.movie?.released.orEmpty(),
        runtime = state.movie?.runtime.orEmpty(),
        genre = state.movie?.genre.orEmpty(),
        director = state.movie?.director.orEmpty(),
        language = state.movie?.language.orEmpty(),
        country = state.movie?.country.orEmpty(),
        plot = state.movie?.plot.orEmpty(),
        isLoading = state.isLoading,
        error = state.error
      )
    })
    binder.bind(view to feature using { uiEvent ->
      when (uiEvent) {
        UiEvent.BackClicked -> Wish.Exit
        UiEvent.Retry -> Wish.RetryLoadingMovie
      }
    })
  }
}