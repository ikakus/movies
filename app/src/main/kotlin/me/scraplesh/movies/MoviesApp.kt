package me.scraplesh.movies

import android.app.Application
import com.facebook.stetho.Stetho
import me.scraplesh.movies.di.movieModule
import me.scraplesh.movies.di.moviesModule
import me.scraplesh.movies.di.navigationModule
import me.scraplesh.movies.di.networkModule
import me.scraplesh.movies.di.persistenceModule
import me.scraplesh.movies.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApp : Application() {
  override fun onCreate() {
    super.onCreate()
    setupStetho()

    startKoin {
      androidLogger()
      androidContext(this@MoviesApp)
      modules(
        listOf(
          networkModule,
          persistenceModule,
          repositoryModule,
          navigationModule,
          moviesModule,
          movieModule
        )
      )
    }
  }

  private fun setupStetho() {
    if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
  }
}