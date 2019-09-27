package me.scraplesh.module.di

import dagger.Component
import me.scraplesh.module.MainActivity
import me.scraplesh.module.features.movies.di.MoviesComponent
import me.scraplesh.module.navigation.NavigationComponent

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