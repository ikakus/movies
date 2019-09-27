package me.scraplesh.movies.features.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.jakewharton.rxbinding3.appcompat.navigationClicks
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.visibility
import com.squareup.picasso.Picasso
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_movie.view.*
import me.scraplesh.movies.core.MviCoreView
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieView @Inject constructor() : MviCoreView<MovieView.UiEvent, MovieView.ViewModel>() {
  sealed class UiEvent {
    object BackClicked : UiEvent()
    object Retry : UiEvent()
  }

  class ViewModel(
    val title: String,
    val posterUrl: String,
    val year: String,
    val rating: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val language: String,
    val country: String,
    val plot: String,
    val error: Throwable?,
    val isLoading: Boolean
  )

  private var toolbar by Delegates.observable<Toolbar?>(null) { _, _, toolbar ->
    toolbar?.apply {
      navigationClicks().map { UiEvent.BackClicked }
        .subscribe(uiEvents)
        .addTo(disposeBag)

      states.map { it.title }
        .distinctUntilChanged()
        .subscribe(::setTitle)
        .addTo(disposeBag)
    }
  }
  private var posterImage by Delegates.observable<ImageView?>(null) { _, _, posterImage ->
    posterImage?.apply {
      states.map { it.posterUrl }
        .distinctUntilChanged()
        .subscribe { posterUrl ->
          Picasso.get()
            .load(posterUrl.takeUnless { it.isEmpty() })
            .into(this)
        }
        .addTo(disposeBag)

    }
  }
  private var ratingText by Delegates.observable<TextView?>(null) { _, _, ratingText ->
    ratingText?.apply {
      states.map { it.rating }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var yearText by Delegates.observable<TextView?>(null) { _, _, yearText ->
    yearText?.apply {
      states.map { it.year }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var ratedText by Delegates.observable<TextView?>(null) { _, _, ratedText ->
    ratedText?.apply {
      states.map { it.rated }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var releasedText by Delegates.observable<TextView?>(null) { _, _, releasedText ->
    releasedText?.apply {
      states.map { it.released }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var runtimeText by Delegates.observable<TextView?>(null) { _, _, runtimeText ->
    runtimeText?.apply {
      states.map { it.runtime }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var genreText by Delegates.observable<TextView?>(null) { _, _, genreText ->
    genreText?.apply {
      states.map { it.genre }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var directorText by Delegates.observable<TextView?>(null) { _, _, directorText ->
    directorText?.apply {
      states.map { it.director }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var languageText by Delegates.observable<TextView?>(null) { _, _, languageText ->
    languageText?.apply {
      states.map { it.language }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var countryText by Delegates.observable<TextView?>(null) { _, _, countryText ->
    countryText?.apply {
      states.map { it.country }
        .distinctUntilChanged()
        .subscribe(::setText)
        .addTo(disposeBag)
    }
  }
  private var plotText by Delegates.observable<TextView?>(null) { _, _, plotText ->
    plotText?.apply {
      states.map { it.plot }
        .distinctUntilChanged()
        .subscribe(::setText)
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
  private var retryButton by Delegates.observable<View?>(null) { _, _, retryButton ->
    retryButton?.apply {
      clicks().map { UiEvent.Retry }
        .subscribe(uiEvents)
        .addTo(disposeBag)
    }
  }
  private var loadingView by Delegates.observable<View?>(null) { _, _, errorView ->
    errorView?.apply {
      states.map { it.isLoading }
        .distinctUntilChanged()
        .subscribe(visibility())
        .addTo(disposeBag)
    }
  }

  override fun createView(inflater: LayoutInflater, container: ViewGroup?): View =
    inflater.inflate(R.layout.fragment_movie, container, false).apply {
      toolbar = toolbar_movie
      posterImage = imageview_movie_poster
      ratingText = textview_movie_rating
      yearText = textview_movie_year
      ratedText = textview_movie_rated
      releasedText = textview_movie_released
      runtimeText = textview_movie_runtime
      genreText = textview_movie_genre
      directorText = textview_movie_director
      languageText = textview_movie_language
      countryText = textview_movie_country
      plotText = textview_movie_plot
      errorView = linearlayout_movie_error
      retryButton = button_movie_retry
      loadingView = framelayout_movie_loading
    }
}