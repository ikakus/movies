package me.scraplesh.movies.features.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.scraplesh.movies.core.di.provideDomainComponent
import me.scraplesh.movies.core.entities.BriefMovie
import me.scraplesh.movies.core.platform.argumentNotNull
import me.scraplesh.movies.domain.entities.BriefMovieEntity
import me.scraplesh.movies.features.movie.di.DaggerMovieComponent
import me.scraplesh.movies.features.movie.di.MovieModule
import me.scraplesh.movies.navigation.provideCoordinator
import javax.inject.Inject

class MovieFragment : Fragment() {

  companion object {
    private const val ARG_MOVIE: String = "arg:movie"

    fun newInstance(movie: BriefMovieEntity) = MovieFragment().apply {
      arguments = Bundle().apply { putParcelable(ARG_MOVIE, BriefMovie(movie)) }
    }
  }

  @Inject lateinit var mviView: MovieView
  @Inject lateinit var bindings: MovieBindings
  private val movie: BriefMovie by argumentNotNull(ARG_MOVIE)

  override fun onCreate(savedInstanceState: Bundle?) {
    inject()
    super.onCreate(savedInstanceState)
    lifecycle.addObserver(mviView)
    bindings.setup(mviView)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = mviView.getView(inflater, container)

  private fun inject() {
    DaggerMovieComponent.builder()
      .domainComponent(provideDomainComponent(requireContext()))
      .movieModule(
        MovieModule(
          movie.entity,
          this,
          provideCoordinator(requireActivity())
        )
      )
      .build()
      .inject(this)
  }

}