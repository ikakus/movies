package me.scraplesh.movies.data.repo

import io.reactivex.Observable
import me.scraplesh.movies.data.datasources.ImdbWebApi
import me.scraplesh.movies.data.entities.database.MovieDbEntity
import me.scraplesh.movies.data.entities.database.MoviesDao
import me.scraplesh.movies.domain.entities.MovieEntity
import me.scraplesh.movies.domain.repo.MoviesRepository
import java.util.concurrent.TimeUnit

class RemoteMoviesRepository(private val api: ImdbWebApi, private val moviesDao: MoviesDao) :
  MoviesRepository {

  override fun searchMovies(query: String): Observable<List<MovieEntity>> =
    Observable.concatArrayEager(
      getMoviesFromDb(),
      getMoviesFromApi(query)
    )
      .materialize()
      .filter { !it.isOnError }
      .dematerialize { it }  // FAQ: check before updating RxJava dependency


  override fun getMovie(imdbId: String): Observable<MovieEntity> =
    Observable.concatArrayEager(
      getMovieFromDb(imdbId),
      getMovieFromApi(imdbId)
    )
      .materialize()
      .filter { !it.isOnError }
      .dematerialize { it }  // FAQ: check before updating RxJava dependency


  private fun getMoviesFromDb(): Observable<List<MovieEntity>> = moviesDao.getAllMovies()
    .map { movies -> movies.map { it.entity } }
    .debounce(400, TimeUnit.MILLISECONDS)

  private fun getMoviesFromApi(query: String): Observable<List<MovieEntity>> =
    api.searchMovies(query)
      .map { envelope -> envelope.results.map { it.entity } }
      .doOnNext { storeUsersInDb(it) }

  private fun getMovieFromDb(imdbId: String): Observable<MovieEntity> = moviesDao.getMovie(imdbId)
    .map { movie -> movie.entity }
    .debounce(400, TimeUnit.MILLISECONDS)

  private fun getMovieFromApi(imdbId: String): Observable<MovieEntity> = api.getMovie(imdbId)
    .map { it.entity }
    .doOnNext { updateMovieInDb(it) }

  private fun storeUsersInDb(movies: List<MovieEntity>) {
    moviesDao.insertMovies(movies.map { MovieDbEntity(it) })
  }

  private fun updateMovieInDb(movie: MovieEntity) {
    moviesDao.updateMovie(MovieDbEntity(movie))
  }
}