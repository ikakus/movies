package me.scraplesh.movies.domain.usecases

import io.reactivex.Single
import me.scraplesh.movies.domain.entities.BriefMovieEntity
import me.scraplesh.movies.domain.repo.MoviesRepository

class SearchMoviesUseCase(private val repo: MoviesRepository) : UseCase {
  operator fun invoke(query: String): Single<List<BriefMovieEntity>> = repo.searchMovies(query)
}