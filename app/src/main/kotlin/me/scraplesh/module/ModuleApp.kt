package me.scraplesh.module

import android.app.Application
import com.facebook.stetho.Stetho
import me.scraplesh.module.core.di.DaggerDomainComponent
import me.scraplesh.module.core.di.DomainComponent
import me.scraplesh.module.core.di.DomainModule
import me.scraplesh.module.core.di.HasDomainComponent
import me.scraplesh.module.di.AppComponent
import me.scraplesh.module.di.DaggerAppComponent

class ModuleApp : Application(), HasDomainComponent {
  val appComponent: AppComponent = DaggerAppComponent.builder().build()
  override val domainComponent: DomainComponent = DaggerDomainComponent.builder()
    .domainModule(DomainModule(appComponent.imdbWebApi()))
    .build()

  override fun onCreate() {
    super.onCreate()
    setupStetho()
  }

  private fun setupStetho() {
    if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
  }
}