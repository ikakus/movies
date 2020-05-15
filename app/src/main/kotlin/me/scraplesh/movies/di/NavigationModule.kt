package me.scraplesh.movies.di

import androidx.fragment.app.FragmentActivity
import me.scraplesh.movies.navigation.RootCoordinator
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

val navigationModule = module {
  val cicerone = Cicerone.create(Router())

  single { cicerone.navigatorHolder }
  factory<Navigator> { (activity: FragmentActivity, containerId: Int) ->
    SupportAppNavigator(activity, containerId)
  }
  single { RootCoordinator(cicerone.router) }
}
