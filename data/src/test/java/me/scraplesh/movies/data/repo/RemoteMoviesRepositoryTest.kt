package me.scraplesh.movies.data.repo

import androidx.lifecycle.Observer
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import me.scraplesh.movies.data.datasources.ImdbWebApi
import me.scraplesh.movies.data.entities.database.MoviesDao
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class RemoteMoviesRepositoryTest {
  private val api = mockk<ImdbWebApi>()
  private val moviesDao = mockk<MoviesDao>()

  private lateinit var repository: RemoteMoviesRepository

  @Before
  fun setup() {
    repository = RemoteMoviesRepository(api, moviesDao)
  }

  @Test
  fun loadMovieListFromNetworkTest() {

  }
}