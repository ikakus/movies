package me.scraplesh.module.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.scraplesh.module.ModuleApp
import me.scraplesh.module.R
import me.scraplesh.module.core.provideDomainComponent
import me.scraplesh.module.di.MainModule
import me.scraplesh.module.features.movies.di.DaggerMoviesComponent
import me.scraplesh.module.features.movies.di.MoviesModule
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
      .moviesModule(MoviesModule(this))
      .build()
      .inject(this)
  }
}