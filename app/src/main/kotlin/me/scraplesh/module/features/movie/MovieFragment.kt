package me.scraplesh.module.features.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import me.scraplesh.module.common.platform.argumentNotNull
import me.scraplesh.module.entities.BriefMovie
import me.scraplesh.module.domain.entities.BriefMovieEntity

class MovieFragment : Fragment() {

  companion object {
    private const val ARG_MOVIE: String = "arg:movie"

    fun newInstance(movie: BriefMovieEntity) = MovieFragment().apply {
      arguments = Bundle().apply { putParcelable(ARG_MOVIE, BriefMovie(movie)) }
    }
  }

  private val movie: BriefMovie by argumentNotNull(ARG_MOVIE)

}