package me.scraplesh.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.scraplesh.movies.MoviesApp
import me.scraplesh.movies.R
import me.scraplesh.movies.navigation.HasCoordinator
import me.scraplesh.movies.navigation.Coordinator
import me.scraplesh.movies.navigation.DaggerNavigationComponent
import me.scraplesh.movies.navigation.NavigationComponent
import me.scraplesh.movies.navigation.NavigationEvent
import me.scraplesh.movies.navigation.NavigationModule
import me.scraplesh.movies.ui.di.DaggerMainComponent
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasCoordinator {
  @Inject lateinit var navigatorHolder: NavigatorHolder
  @Inject lateinit var navigator: Navigator
  @Inject override lateinit var coordinator: Coordinator

  override fun onCreate(savedInstanceState: Bundle?) {
    inject()
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    coordinator.accept(ApplicationStarted)
  }

  override fun onResumeFragments() {
    super.onResumeFragments()
    navigatorHolder.setNavigator(navigator)
  }

  private fun navigationComponent(): NavigationComponent = DaggerNavigationComponent.builder()
    .navigationModule(NavigationModule(this, R.id.framelayout_main_container))
    .build()

  private fun inject() {
    DaggerMainComponent.builder()
      .appComponent((application as MoviesApp).appComponent)
      .navigationComponent(navigationComponent())
      .build()
      .inject(this)
  }

  object ApplicationStarted : NavigationEvent
}
