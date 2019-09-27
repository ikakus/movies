package me.scraplesh.module.di

import dagger.Module
import dagger.Provides
import me.scraplesh.module.MainCoordinator
import me.scraplesh.module.navigation.Coordinator
import ru.terrakok.cicerone.Router

@Module
class MainModule {
  @MainScope
  @Provides
  fun coordinator(router: Router): Coordinator = MainCoordinator(router)
}