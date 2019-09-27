package me.scraplesh.movies.data.entities

import me.scraplesh.movies.domain.entities.Entity

interface MappingData<out E : Entity> {
  val entity: E
}
