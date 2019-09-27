package me.scraplesh.module

import me.scraplesh.module.domain.entities.BriefMovieEntity
import me.scraplesh.module.navigation.Coordinator
import me.scraplesh.module.navigation.NavigationEvent
import ru.terrakok.cicerone.Router

class MainCoordinator(private val router: Router) : Coordinator {
  sealed class MainEvent : NavigationEvent {
    object ApplicationStarted : MainEvent()
    object ExitMovieRequested : MainEvent()
    class MovieSelected(val movie: BriefMovieEntity) : MainEvent()
  }

  override fun accept(event: NavigationEvent) {
    when (event) {
      MainEvent.ApplicationStarted -> router.newRootScreen(
        ModuleScreen.MoviesScreen
      )
      is MainEvent.MovieSelected -> router.navigateTo(
        ModuleScreen.MovieScreen(event.movie)
      )
      MainEvent.ExitMovieRequested -> router.exit()
    }
  }


}
