package me.scraplesh.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.badoo.mvicore.android.AndroidBindings
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf

class MoviesFragment : Fragment() {
  private val bindings: AndroidBindings<MoviesView> by currentScope.inject { parametersOf(this) }
  private val mviView: MoviesView by currentScope.inject()

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