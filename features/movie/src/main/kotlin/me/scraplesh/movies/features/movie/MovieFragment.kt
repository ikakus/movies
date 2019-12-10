package me.scraplesh.movies.features.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.badoo.mvicore.android.AndroidBindings
import me.scraplesh.movies.core.entities.BriefMovie
import me.scraplesh.movies.core.platform.argumentNotNull
import me.scraplesh.movies.domain.entities.BriefMovieEntity
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf

class MovieFragment : Fragment() {

  companion object {
    private const val ARG_MOVIE: String = "arg:movie"

    fun newInstance(movie: BriefMovieEntity) = MovieFragment().apply {
      arguments = Bundle().apply { putParcelable(ARG_MOVIE, BriefMovie(movie)) }
    }
  }

  private val bindings: AndroidBindings<MovieView> by currentScope.inject {
    parametersOf(movie.entity, this)
  }
  private val mviView: MovieView by currentScope.inject()
  private val movie: BriefMovie by argumentNotNull(ARG_MOVIE)

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