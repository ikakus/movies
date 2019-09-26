package me.scraplesh.module

import me.scraplesh.module.features.movies.MoviesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class ModuleScreen : SupportAppScreen() {
  object MoviesScreen : ModuleScreen() {
    override fun getFragment() = MoviesFragment()
  }
}
