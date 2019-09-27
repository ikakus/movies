package me.scraplesh.module.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.scraplesh.module.core.di.provideDomainComponent
import me.scraplesh.module.features.movies.di.DaggerMoviesComponent
import me.scraplesh.module.features.movies.di.MoviesModule
import me.scraplesh.module.navigation.provideCoordinator
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