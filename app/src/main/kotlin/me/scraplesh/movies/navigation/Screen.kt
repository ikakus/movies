package me.scraplesh.movies.navigation

import androidx.fragment.app.Fragment
import me.scraplesh.features.movie.MovieFragment
import me.scraplesh.features.movies.MoviesFragment
import me.scraplesh.movies.domain.entities.MovieEntity
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screen : SupportAppScreen() {
  object MoviesScreen : Screen() {
    override fun getFragment(): Fragment = MoviesFragment()
  }

  class MovieScreen(private val movie: MovieEntity) : Screen() {
    override fun getFragment(): Fragment = MovieFragment.newInstance(movie)
  }
}
