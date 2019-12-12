package me.scraplesh.movies.navigation

import me.scraplesh.movies.domain.entities.MovieEntity

sealed class NavigationEvent {
  object ApplicationStarted : NavigationEvent()
  class ShowMovieScreen(val movie: MovieEntity) : NavigationEvent()
  object ExitMovieScreen : NavigationEvent()
}