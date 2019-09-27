package me.scraplesh.module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.scraplesh.module.core.HasCoordinator
import me.scraplesh.module.di.DaggerMainComponent
import me.scraplesh.module.navigation.Coordinator
import me.scraplesh.module.navigation.DaggerNavigationComponent
import me.scraplesh.module.navigation.NavigationComponent
import me.scraplesh.module.navigation.NavigationEvent
import me.scraplesh.module.navigation.NavigationModule
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
      .appComponent((application as ModuleApp).appComponent)
      .navigationComponent(navigationComponent())
      .build()
      .inject(this)
  }

  object ApplicationStarted : NavigationEvent
}
