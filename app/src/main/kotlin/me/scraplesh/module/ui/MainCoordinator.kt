package me.scraplesh.module.ui

import me.scraplesh.module.BuildConfig
import me.scraplesh.module.ModuleScreen
import me.scraplesh.module.features.movie.MovieFeature
import me.scraplesh.module.features.movies.MoviesFeature
import me.scraplesh.module.navigation.Coordinator
import me.scraplesh.module.navigation.NavigationEvent
import ru.terrakok.cicerone.Router

class MainCoordinator(private val router: Router) : Coordinator {
  override fun accept(event: NavigationEvent) {
    when (event) {
      MainActivity.ApplicationStarted -> router.newRootScreen(
        ModuleScreen.MoviesScreen
      )
      is MoviesFeature.News.MovieSelected -> {
        if (BuildConfig.movieEnabled) router.navigateTo(ModuleScreen.MovieScreen(event.movie))
      }
      MovieFeature.News.ExitRequested -> router.exit()
    }
  }

}
