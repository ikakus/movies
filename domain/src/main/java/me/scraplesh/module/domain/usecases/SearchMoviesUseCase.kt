package me.scraplesh.module.domain.usecases

import io.reactivex.Single
import me.scraplesh.module.domain.entities.BriefMovieEntity
import me.scraplesh.module.domain.repo.MoviesRepository

class SearchMoviesUseCase(private val repo: MoviesRepository) : UseCase {
  operator fun invoke(query: String): Single<List<BriefMovieEntity>> = repo.searchMovies(query)
}