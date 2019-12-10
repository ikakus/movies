package me.scraplesh.movies.navigation

import androidx.fragment.app.Fragment
import me.scraplesh.movies.features.movie.MovieFragment
import me.scraplesh.movies.features.movies.MoviesFragment
import me.scraplesh.movies.domain.entities.BriefMovieEntity
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screen : SupportAppScreen() {
  object MoviesScreen : Screen() {
    override fun getFragment(): Fragment = MoviesFragment()
  }

  class MovieScreen(private val movie: BriefMovieEntity) : Screen() {
    override fun getFragment(): Fragment = MovieFragment.newInstance(movie)
  }
}
