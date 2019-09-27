package me.scraplesh.movies.ui.di

import dagger.Module
import dagger.Provides
import me.scraplesh.movies.ui.MainCoordinator
import me.scraplesh.movies.navigation.Coordinator
import ru.terrakok.cicerone.Router

@Module
class MainModule {
  @MainScope
  @Provides
  fun coordinator(router: Router): Coordinator = MainCoordinator(router)
}