package me.scraplesh.movies.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.scraplesh.movies.core.di.provideDomainComponent
import me.scraplesh.movies.features.movies.di.DaggerMoviesComponent
import me.scraplesh.movies.features.movies.di.MoviesModule
import me.scraplesh.movies.navigation.provideCoordinator
import javax.inject.Inject

class MoviesFragment : Fragment() {
  @Inject lateinit var mviView: MoviesView
  @Inject lateinit var bindings: MoviesBindings

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
    DaggerMoviesComponent.builder()
      .domainComponent(provideDomainComponent(requireContext()))
      .moviesModule(MoviesModule(this,
        provideCoordinator(requireActivity())
      ))
      .build()
      .inject(this)
  }
}