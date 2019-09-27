package me.scraplesh.movies.ui.di

import dagger.Component
import me.scraplesh.movies.di.AppComponent
import me.scraplesh.movies.ui.MainActivity
import me.scraplesh.movies.navigation.NavigationComponent

@MainScope
@Component(
  modules = [MainModule::class],
  dependencies = [
    AppComponent::class,
    NavigationComponent::class
  ]
)
interface MainComponent {
  fun inject(activity: MainActivity)
}