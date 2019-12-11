package me.scraplesh.movies.domain.entities

interface Mapping<out E: Entity> {
  val entity: E
}