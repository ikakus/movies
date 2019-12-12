package me.scraplesh.movies.di

import androidx.room.Room
import me.scraplesh.movies.BuildConfig
import me.scraplesh.movies.data.datasources.MoviesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceModule = module {
  single {
    Room.databaseBuilder(androidContext(), MoviesDatabase::class.java, BuildConfig.database)
      .fallbackToDestructiveMigration()
      .build()
  }
}