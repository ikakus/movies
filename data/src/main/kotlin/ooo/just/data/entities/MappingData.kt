package ooo.just.data.entities

import ooo.just.domain.entities.Entity

interface MappingData<out E : Entity> {
  val entity: E
}
