package me.scraplesh.movies.navigation

import dagger.Component
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Component(modules = [NavigationModule::class])
interface NavigationComponent {
  fun navigationHolder(): NavigatorHolder
  fun navigator(): Navigator
  fun router(): Router
}