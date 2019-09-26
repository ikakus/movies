package me.scraplesh.module

import androidx.fragment.app.Fragment
import me.scraplesh.module.features.movie.MovieFragment
import me.scraplesh.module.features.movies.MoviesFragment
import me.scraplesh.module.domain.entities.BriefMovieEntity
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class ModuleScreen : SupportAppScreen() {
  object MoviesScreen : ModuleScreen() {
    override fun getFragment(): Fragment = MoviesFragment()
  }

  class MovieScreen(private val movie: BriefMovieEntity) : ModuleScreen() {
    override fun getFragment(): Fragment = MovieFragment.newInstance(movie)
  }
}
