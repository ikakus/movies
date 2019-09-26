package me.scraplesh.module.data.entities

import me.scraplesh.module.domain.entities.Entity

interface MappingData<out E : Entity> {
  val entity: E
}
