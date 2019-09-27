package me.scraplesh.module.features.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.PublishRelay
import com.squareup.picasso.Picasso
import io.reactivex.ObservableSource
import io.reactivex.Observer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movies.*
import me.scraplesh.module.R
import me.scraplesh.module.domain.entities.BriefMovieEntity
import java.util.function.Consumer
import javax.inject.Inject

class MoviesAdapter @Inject constructor() :
  RecyclerView.Adapter<MoviesAdapter.ViewHolder>(),
  Consumer<List<BriefMovieEntity>>,
  ObservableSource<BriefMovieEntity> {

  private var movies: List<BriefMovieEntity> = emptyList()
  private val selectedMovies = PublishRelay.create<BriefMovieEntity>()

  override fun getItemCount(): Int = movies.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
    LayoutInflater.from(parent.context)
      .inflate(R.layout.item_movies, parent, false)
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    movies[position].let { movie ->
      holder.apply {
        Picasso.get()
          .load(movie.posterUrl)
          .into(imageview_itemmovies)

        textview_itemmovies_title.text = movie.title
        textview_itemmovies_year.text = movie.year
      }
    }
  }

  override fun accept(newItems: List<BriefMovieEntity>) {
    movies = newItems
  }

  override fun subscribe(observer: Observer<in BriefMovieEntity>) {
    selectedMovies.subscribe(observer)
  }

  class ViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer

}
