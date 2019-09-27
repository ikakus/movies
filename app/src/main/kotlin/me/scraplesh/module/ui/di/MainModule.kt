package me.scraplesh.module.ui.di

import dagger.Module
import dagger.Provides
import me.scraplesh.module.ui.MainCoordinator
import me.scraplesh.module.navigation.Coordinator
import ru.terrakok.cicerone.Router

@Module
class MainModule {
  @MainScope
  @Provides
  fun coordinator(router: Router): Coordinator = MainCoordinator(router)
}