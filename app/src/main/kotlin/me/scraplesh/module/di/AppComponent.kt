package me.scraplesh.module.di

import dagger.Component
import me.scraplesh.module.data.datasources.ImdbWebApi
import me.scraplesh.module.navigation.NavigationComponent
import me.scraplesh.module.navigation.NavigationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
  fun imdbWebApi(): ImdbWebApi
}