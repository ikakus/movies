package me.scraplesh.movies.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module
class NavigationModule(private val activity: FragmentActivity, @IdRes private val containerId: Int) {
  private val cicerone = Cicerone.create(Router())

  @Provides
  fun navigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

  @Provides
  fun navigator(): Navigator = SupportAppNavigator(activity, containerId)

  @Provides
  fun router(): Router = cicerone.router
}