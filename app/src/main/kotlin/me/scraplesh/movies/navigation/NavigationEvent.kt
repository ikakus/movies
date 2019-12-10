package me.scraplesh.movies.navigation

import me.scraplesh.movies.domain.entities.BriefMovieEntity

sealed class NavigationEvent {
  object ApplicationStarted : NavigationEvent()
  class ShowMovieScreen(val movie: BriefMovieEntity) : NavigationEvent()
  object ExitMovieScreen : NavigationEvent()
}