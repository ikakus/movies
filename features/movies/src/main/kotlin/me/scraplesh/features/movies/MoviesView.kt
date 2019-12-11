package me.scraplesh.features.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.visibility
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_movies.view.button_movies_retry
import kotlinx.android.synthetic.main.fragment_movies.view.framelayout_movies_loading
import kotlinx.android.synthetic.main.fragment_movies.view.group_movies_error
import kotlinx.android.synthetic.main.fragment_movies.view.recyclerview_movies
import kotlinx.android.synthetic.main.fragment_movies.view.textview_movies_no_content
import me.scraplesh.movies.core.MviCoreView
import me.scraplesh.movies.domain.entities.MovieEntity
import kotlin.properties.Delegates

class MoviesView(moviesAdapter: MoviesAdapter) :
    MviCoreView<MoviesView.UiEvent, MoviesView.ViewModel>() {

  sealed class UiEvent {
    object Retry : UiEvent()
    class MovieSelected(val movie: MovieEntity) : UiEvent()
  }

  class ViewModel(
      val movies: List<MovieEntity>,
      val isLoading: Boolean,
      val noContent: Boolean,
      val error: Throwable?
  )

  private var moviesList by Delegates.observable<RecyclerView?>(null) { _, _, moviesList ->
    moviesList?.apply {
      layoutManager = GridLayoutManager(context, 2)
      adapter = moviesAdapter.apply {
        states.map { it.movies }
            .distinctUntilChanged { movies, newMovies ->
              movies.size == newMovies.size &&
                  newMovies.all { newMovie -> movies.any { movie -> movie.imdbId == newMovie.imdbId } }
            }
            .subscribe { newMovies ->
              accept(newMovies)
              notifyDataSetChanged()
            }
            .addTo(disposeBag)

        Observable.wrap(this)
            .map { movie -> UiEvent.MovieSelected(movie) }
            .subscribe(uiEvents)
            .addTo(disposeBag)
      }

      states.map { !it.noContent }
          .subscribe(visibility())
          .addTo(disposeBag)

    }
  }
  private var loadingView by Delegates.observable<View?>(null) { _, _, loadingView ->
    loadingView?.apply {
      states.map { it.isLoading }
          .distinctUntilChanged()
          .subscribe(visibility())
          .addTo(disposeBag)

    }
  }
  private var noContentView by Delegates.observable<View?>(null) { _, _, noContentView ->
    noContentView?.apply {
      states.map { it.noContent }
          .distinctUntilChanged()
          .subscribe(visibility())
          .addTo(disposeBag)
    }
  }
  private var errorView by Delegates.observable<View?>(null) { _, _, errorView ->
    errorView?.apply {
      states.map { it.error != null }
          .distinctUntilChanged()
          .subscribe(visibility())
          .addTo(disposeBag)
    }
  }
  private var buttonRetry by Delegates.observable<View?>(null) { _, _, buttonRetry ->
    buttonRetry?.apply {
      clicks().map { UiEvent.Retry }
          .subscribe(uiEvents)
          .addTo(disposeBag)
    }
  }

  override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
    return inflater.inflate(R.layout.fragment_movies, container, false).apply {
      moviesList = recyclerview_movies
      loadingView = framelayout_movies_loading
      noContentView = textview_movies_no_content
      errorView = group_movies_error
      buttonRetry = button_movies_retry
    }
  }

}