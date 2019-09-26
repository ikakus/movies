package me.scraplesh.module

import io.reactivex.functions.Consumer
import me.scraplesh.module.domain.entities.BriefMovieEntity
import ru.terrakok.cicerone.Router

class MainCoordinator(private val router: Router) : Consumer<MainCoordinator.NavigationEvent> {
  sealed class NavigationEvent {
    object ApplicationStarted : NavigationEvent()
    class MovieSelected(val movie: BriefMovieEntity) : NavigationEvent()
  }

  override fun accept(event: NavigationEvent) {
    when (event) {
      NavigationEvent.ApplicationStarted -> router.newRootScreen(ModuleScreen.MoviesScreen)
      is NavigationEvent.MovieSelected -> router.navigateTo(ModuleScreen.MovieScreen(event.movie))
    }
  }


}
