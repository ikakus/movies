package me.scraplesh.movies

import android.app.Application
import com.facebook.stetho.Stetho
import me.scraplesh.movies.core.di.DaggerDomainComponent
import me.scraplesh.movies.core.di.DomainComponent
import me.scraplesh.movies.core.di.DomainModule
import me.scraplesh.movies.core.di.HasDomainComponent
import me.scraplesh.movies.di.AppComponent
import me.scraplesh.movies.di.DaggerAppComponent

class MoviesApp : Application(), HasDomainComponent {
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