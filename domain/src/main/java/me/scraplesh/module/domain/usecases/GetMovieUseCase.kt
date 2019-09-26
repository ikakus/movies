package me.scraplesh.module.domain.usecases

import io.reactivex.Single
import me.scraplesh.module.domain.entities.MovieEntity
import me.scraplesh.module.domain.repo.MoviesRepository

class GetMovieUseCase(private val repo: MoviesRepository) : UseCase {
  operator fun invoke(imdbId: String): Single<MovieEntity> = repo.getMovie(imdbId)
}
