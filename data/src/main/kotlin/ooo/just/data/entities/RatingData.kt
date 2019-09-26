package ooo.just.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ooo.just.domain.entities.RatingEntity

/*
{
    "Source": "Internet Movie Database",
    "Value": "7.6/10"
}
 */
@JsonClass(generateAdapter = true)
data class RatingData(
  @Json(name = SOURCE) val source: String,
  @Json(name = VALUE) val value: String
) : MappingData<RatingEntity> {

  override val entity: RatingEntity get() = RatingEntity(source, value)

  private companion object {
    const val SOURCE = "Source"
    const val VALUE = "Value"
  }
}
