package me.scraplesh.movies.data.repo

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import me.scraplesh.movies.data.datasources.ImdbWebApi
import me.scraplesh.movies.data.entities.database.MovieDbEntity
import me.scraplesh.movies.data.entities.database.MoviesDao
import me.scraplesh.movies.domain.entities.MovieEntity
import me.scraplesh.movies.domain.repo.MoviesRepository
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

class RemoteMoviesRepository(private val api: ImdbWebApi, private val moviesDao: MoviesDao) :
  MoviesRepository {

  override fun searchMovies(query: String): Observable<List<MovieEntity>> =
    Observable.concatArrayEagerDelayError(
      getMoviesFromDb(),
      getMoviesFromApi(query)
    )

  override fun getMovie(imdbId: String): Observable<MovieEntity> =
    Observable.concatArrayEagerDelayError(
      getMovieFromDb(imdbId),
      getMovieFromApi(imdbId)
    )

  private fun getMoviesFromDb(): Observable<List<MovieEntity>> = moviesDao.getAllMovies()
    .subscribeOn(Schedulers.io())
    .map { movies -> movies.map { it.entity } }
    .delay(400, TimeUnit.MILLISECONDS)
    .toObservable()

  private fun getMoviesFromApi(query: String): Observable<List<MovieEntity>> =
    api.searchMovies(query)
      .map { envelope -> envelope.results.map { it.entity } }
      .doOnSuccess { storeMoviesInDb(it) }
      .toObservable()
      .delay(1, TimeUnit.SECONDS)
      .flatMap { Observable.error<List<MovieEntity>>(IllegalArgumentException("trololo")) }

  private fun getMovieFromDb(imdbId: String): Observable<MovieEntity> = moviesDao.getMovie(imdbId)
    .subscribeOn(Schedulers.io())
    .map { movie -> movie.entity }
    .delay(400, TimeUnit.MILLISECONDS)
    .toObservable()

  private fun getMovieFromApi(imdbId: String): Observable<MovieEntity> = api.getMovie(imdbId)
    .map { it.entity }
    .doOnSuccess { updateMovieInDb(it) }
    .toObservable()

  private fun storeMoviesInDb(movies: List<MovieEntity>) {
    moviesDao.insertMovies(movies.map { MovieDbEntity(it) })
  }

  private fun updateMovieInDb(movie: MovieEntity) {
    moviesDao.updateMovie(MovieDbEntity(movie))
  }
}