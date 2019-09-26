package me.scraplesh.module.features.movies

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import me.scraplesh.module.features.movies.MoviesFeature.Wish
import me.scraplesh.module.features.movies.MoviesFeature.State
import me.scraplesh.module.features.movies.MoviesFeature.Effect
import me.scraplesh.module.features.movies.MoviesFeature.News
import me.scraplesh.module.domain.entities.BriefMovieEntity
import me.scraplesh.module.domain.usecases.SearchMoviesUseCase

class MoviesFeature(initialState: State, searchMoviesUseCase: SearchMoviesUseCase) :
  ActorReducerFeature<Wish, Effect, State, News>(
    initialState = initialState,
    bootstrapper = MoviesBootstrapper(),
    actor = MoviesActor(searchMoviesUseCase),
    reducer = MoviesReducer(),
    newsPublisher = MoviesNewsPublisher()
  ) {

  data class State(
    val defaultQuery: String,
    val movies: List<BriefMovieEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
  )

  sealed class Wish {
    object LoadMovies : Wish()
    object RetryLoadingMovies : Wish()
    class ShowMovie(val movie: BriefMovieEntity) : Wish()
  }

  sealed class Effect {
    object LoadingStarted : Effect()
    object NoEffect : Effect()
    class ErrorOccurred(val error: Throwable) : Effect()
    class MoviesLoaded(val movies: List<BriefMovieEntity>) : Effect()
  }

  sealed class News {
    class MovieSelected(val movie: BriefMovieEntity) : News()
  }

  class MoviesBootstrapper : Bootstrapper<Wish> {
    override fun invoke(): Observable<Wish> {
      return Observable.just(Wish.LoadMovies)
    }
  }

  class MoviesActor(private val searchMoviesUseCase: SearchMoviesUseCase) :
    Actor<State, Wish, Effect> {

    override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
      Wish.LoadMovies -> loadMovies(state.defaultQuery)
      is Wish.ShowMovie -> noEffect()
      Wish.RetryLoadingMovies -> loadMovies(state.defaultQuery)
    }
      .onErrorReturn { error: Throwable -> Effect.ErrorOccurred(error) }
      .observeOn(AndroidSchedulers.mainThread())

    private fun noEffect(): Observable<Effect> = Observable.just(Effect.NoEffect)

    private fun loadMovies(query: String): Observable<Effect> =
      searchMoviesUseCase(query).map<Effect> { movies -> Effect.MoviesLoaded(movies) }
        .toObservable()
        .startWith(Effect.LoadingStarted)
  }

  class MoviesReducer : Reducer<State, Effect> {
    override fun invoke(state: State, effect: Effect): State = when (effect) {
      Effect.LoadingStarted -> state.copy(isLoading = true, error = null)
      is Effect.ErrorOccurred -> state.copy(error = effect.error, isLoading = false)
      is Effect.MoviesLoaded -> state.copy(movies = effect.movies, isLoading = false)
      Effect.NoEffect -> state
    }
  }

  class MoviesNewsPublisher : NewsPublisher<Wish, Effect, State, News> {
    override fun invoke(wish: Wish, effect: Effect, state: State): News? {
      return when (wish) {
        is Wish.ShowMovie -> News.MovieSelected(wish.movie)
        else -> null
      }
    }
  }

}