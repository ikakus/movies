package me.scraplesh.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.scraplesh.movies.R
import me.scraplesh.movies.navigation.NavigationEvent
import me.scraplesh.movies.navigation.RootCoordinator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

class MainActivity : AppCompatActivity() {
  private val navigatorHolder: NavigatorHolder by inject()
  private val navigator: Navigator by inject { parametersOf(this, R.id.framelayout_main_container) }
  private val coordinator: RootCoordinator by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    coordinator.accept(NavigationEvent.ApplicationStarted)
  }

  override fun onResumeFragments() {
    super.onResumeFragments()
    navigatorHolder.setNavigator(navigator)
  }
}
