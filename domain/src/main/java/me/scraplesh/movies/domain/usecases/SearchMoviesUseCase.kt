package me.scraplesh.movies.domain.usecases

import io.reactivex.Observable
import me.scraplesh.movies.domain.entities.MovieEntity
import me.scraplesh.movies.domain.repo.MoviesRepository

class SearchMoviesUseCase(private val repo: MoviesRepository) : UseCase {
  operator fun invoke(query: String): Observable<List<MovieEntity>> = repo.searchMovies(query)
}