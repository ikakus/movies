package me.scraplesh.movies.di

import dagger.Component
import me.scraplesh.movies.data.datasources.ImdbWebApi
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
  fun imdbWebApi(): ImdbWebApi
}