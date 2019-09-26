package me.scraplesh.module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import me.scraplesh.module.MainCoordinator.NavigationEvent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {
  private val cicerone = Cicerone.create(Router())
  private val navigatorHolder: NavigatorHolder get() = cicerone.navigatorHolder
  private val navigator: Navigator = SupportAppNavigator(this, R.id.framelayout_main_container)
  private val coordinator: MainCoordinator = MainCoordinator(cicerone.router)

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
