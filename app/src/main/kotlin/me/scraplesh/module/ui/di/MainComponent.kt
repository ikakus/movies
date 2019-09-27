package me.scraplesh.module.ui.di

import dagger.Component
import me.scraplesh.module.di.AppComponent
import me.scraplesh.module.ui.MainActivity
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