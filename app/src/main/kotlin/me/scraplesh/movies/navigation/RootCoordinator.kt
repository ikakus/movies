package me.scraplesh.movies.navigation

import io.reactivex.functions.Consumer
import me.scraplesh.movies.BuildConfig
import ru.terrakok.cicerone.Router

class RootCoordinator(private val router: Router) : Consumer<NavigationEvent> {
  override fun accept(event: NavigationEvent) {
    when (event) {
      NavigationEvent.ApplicationStarted -> router.newRootScreen(Screen.MoviesScreen)
      is NavigationEvent.ShowMovieScreen -> {
        if (BuildConfig.movieEnabled) router.navigateTo(Screen.MovieScreen(event.movie))
      }
      NavigationEvent.ExitMovieScreen -> router.exit()
    }
  }
}
