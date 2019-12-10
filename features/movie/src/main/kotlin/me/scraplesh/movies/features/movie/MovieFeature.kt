package me.scraplesh.movies.features.movie

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import me.scraplesh.movies.domain.entities.MovieEntity
import me.scraplesh.movies.domain.usecases.GetMovieUseCase
import me.scraplesh.movies.features.movie.MovieFeature.Effect
import me.scraplesh.movies.features.movie.MovieFeature.News
import me.scraplesh.movies.features.movie.MovieFeature.State
import me.scraplesh.movies.features.movie.MovieFeature.Wish

class MovieFeature(initialState: State, getMovie: GetMovieUseCase) :
  ActorReducerFeature<Wish, Effect, State, News>(
    initialState = initialState,
    bootstrapper = MovieBootstrapper(),
    actor = MovieActor(getMovie),
    reducer = MovieReducer(),
    newsPublisher = MovieNewsPublisher()
  ) {

  data class State(
    val imdbId: String,
    val movie: MovieEntity? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
  )

  sealed class Wish {
    object LoadMovies : Wish()
    object RetryLoadingMovie : Wish()
    object Exit : Wish()
  }

  sealed class Effect {
    object LoadingStarted : Effect()
    object NoEffect : Effect()
    class ErrorOccurred(val error: Throwable) : Effect()
    class MovieLoaded(val movie: MovieEntity) : Effect()
  }

  sealed class News {
    object ExitRequested : News()
  }

  class MovieBootstrapper : Bootstrapper<Wish> {
    override fun invoke(): Observable<Wish> {
      return Observable.just(Wish.LoadMovies)
    }
  }

  class MovieActor(private val getMovie: GetMovieUseCase) :
    Actor<State, Wish, Effect> {

    override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
      Wish.LoadMovies -> loadMovie(state.imdbId)
      is Wish.Exit -> noEffect()
      Wish.RetryLoadingMovie -> loadMovie(state.imdbId)
    }
      .onErrorReturn { error: Throwable -> Effect.ErrorOccurred(error) }
      .observeOn(AndroidSchedulers.mainThread())

    private fun noEffect(): Observable<Effect> = Observable.just(Effect.NoEffect)

    private fun loadMovie(imdbId: String): Observable<Effect> =
      getMovie(imdbId).map<Effect> { movie -> Effect.MovieLoaded(movie) }
        .toObservable()
        .startWith(Effect.LoadingStarted)
  }

  class MovieReducer : Reducer<State, Effect> {
    override fun invoke(state: State, effect: Effect): State = when (effect) {
      Effect.LoadingStarted -> state.copy(isLoading = true, error = null)
      is Effect.ErrorOccurred -> state.copy(error = effect.error, isLoading = false)
      is Effect.MovieLoaded -> state.copy(movie = effect.movie, isLoading = false)
      Effect.NoEffect -> state
    }
  }

  class MovieNewsPublisher : NewsPublisher<Wish, Effect, State, News> {
    override fun invoke(wish: Wish, effect: Effect, state: State): News? {
      return when (wish) {
        is Wish.Exit -> News.ExitRequested
        else -> null
      }
    }
  }

}