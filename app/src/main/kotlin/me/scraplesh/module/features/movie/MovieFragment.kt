package me.scraplesh.module.features.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.scraplesh.module.common.platform.argumentNotNull
import me.scraplesh.module.domain.entities.BriefMovieEntity
import me.scraplesh.module.entities.BriefMovie

class MovieFragment : Fragment() {

  companion object {
    private const val ARG_MOVIE: String = "arg:movie"

    fun newInstance(movie: BriefMovieEntity) = MovieFragment().apply {
      arguments = Bundle().apply { putParcelable(ARG_MOVIE, BriefMovie(movie)) }
    }
  }

  private lateinit var mviView: MovieView
  private lateinit var bindings: MovieBindings
  private val movie: BriefMovie by argumentNotNull(ARG_MOVIE)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycle.addObserver(mviView)
    bindings.setup(mviView)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = mviView.getView(inflater, container)

}