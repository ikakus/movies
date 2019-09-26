package me.scraplesh.module.domain.repo

import io.reactivex.Single
import me.scraplesh.module.domain.entities.BriefMovieEntity

interface MoviesRepository {
  fun searchMovies(query: String): Single<List<BriefMovieEntity>>
}