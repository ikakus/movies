package me.scraplesh.movies

import android.app.Application
import com.facebook.stetho.Stetho
import me.scraplesh.movies.di.appModule
import me.scraplesh.movies.di.domainModule
import me.scraplesh.movies.di.movieModule
import me.scraplesh.movies.di.moviesModule
import me.scraplesh.movies.di.mainModule
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
              appModule,
              domainModule,
              mainModule,
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