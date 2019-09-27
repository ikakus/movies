package me.scraplesh.module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.scraplesh.module.MainCoordinator.MainEvent
import me.scraplesh.module.di.DaggerMainComponent
import me.scraplesh.module.di.MainModule
import me.scraplesh.module.navigation.Coordinator
import me.scraplesh.module.navigation.DaggerNavigationComponent
import me.scraplesh.module.navigation.NavigationComponent
import me.scraplesh.module.navigation.NavigationModule
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
  @Inject lateinit var navigatorHolder: NavigatorHolder
  @Inject lateinit var navigator: Navigator
  @Inject lateinit var coordinator: Coordinator

  override fun onCreate(savedInstanceState: Bundle?) {
    inject()
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    coordinator.accept(MainEvent.ApplicationStarted)
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
      .appComponent((application as ModuleApp).appComponent)
      .navigationComponent(navigationComponent())
      .build()
      .inject(this)
  }
}
