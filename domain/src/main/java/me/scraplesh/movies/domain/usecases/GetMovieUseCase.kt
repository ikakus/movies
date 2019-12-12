package me.scraplesh.movies.domain.usecases

import io.reactivex.Observable
import me.scraplesh.movies.domain.entities.MovieEntity
import me.scraplesh.movies.domain.repo.MoviesRepository

class GetMovieUseCase(private val repo: MoviesRepository) : UseCase {
  operator fun invoke(imdbId: String): Observable<MovieEntity> = repo.getMovie(imdbId)
}
