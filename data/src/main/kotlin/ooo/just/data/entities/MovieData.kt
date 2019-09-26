package ooo.just.data.entities

import ooo.just.domain.entities.MovieEntity

class MovieData : MappingData<MovieEntity> {
    override val entity: MovieEntity get() = MovieEntity()
}
