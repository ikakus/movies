package me.scraplesh.features.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.badoo.mvicore.android.AndroidBindings
import me.scraplesh.movies.core.entities.Movie
import me.scraplesh.movies.core.platform.argumentNotNull
import me.scraplesh.movies.domain.entities.MovieEntity
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf

class MovieFragment : Fragment() {

  companion object {
    private const val ARG_MOVIE: String = "arg:movie"

    fun newInstance(movie: MovieEntity) = MovieFragment().apply {
      arguments = Bundle().apply { putParcelable(ARG_MOVIE, Movie(movie)) }
    }
  }

  private val bindings: AndroidBindings<MovieView> by currentScope.inject {
    parametersOf(movie.entity, this)
  }
  private val mviView: MovieView by currentScope.inject()
  private val movie: Movie by argumentNotNull(ARG_MOVIE)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bindings.setup(mviView)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = mviView.getView(inflater, container)

}