package me.scraplesh.movies.ui

import me.scraplesh.movies.BuildConfig
import me.scraplesh.movies.Screen
import me.scraplesh.movies.features.movie.MovieFeature
import me.scraplesh.movies.features.movies.MoviesFeature
import me.scraplesh.movies.navigation.Coordinator
import me.scraplesh.movies.navigation.NavigationEvent
import ru.terrakok.cicerone.Router

class MainCoordinator(private val router: Router) : Coordinator {
  override fun accept(event: NavigationEvent) {
    when (event) {
      MainActivity.ApplicationStarted -> router.newRootScreen(
        Screen.MoviesScreen
      )
      is MoviesFeature.News.MovieSelected -> {
        if (BuildConfig.movieEnabled) router.navigateTo(Screen.MovieScreen(event.movie))
      }
      MovieFeature.News.ExitRequested -> router.exit()
    }
  }

}
