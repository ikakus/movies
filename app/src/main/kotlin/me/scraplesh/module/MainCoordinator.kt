package me.scraplesh.module

import io.reactivex.functions.Consumer
import ru.terrakok.cicerone.Router

class MainCoordinator(private val router: Router) : Consumer<MainCoordinator.NavigationEvent> {
  enum class NavigationEvent {
    ApplicationStarted
  }

  override fun accept(event: NavigationEvent) {
    when (event) {
      NavigationEvent.ApplicationStarted -> router.newRootScreen(ModuleScreen.MoviesScreen)
    }
  }


}
