package me.scraplesh.movies.core.platform

import androidx.fragment.app.Fragment

inline fun <reified T: Any> Fragment.argumentNotNull(key: String, default: T? = null) = lazy {
  val value = arguments?.get(key)
  requireNotNull(if (value is T) value else default) { key }
}
