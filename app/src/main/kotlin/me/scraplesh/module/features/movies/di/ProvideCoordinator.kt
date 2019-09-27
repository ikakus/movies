package me.scraplesh.module.features.movies.di

import android.app.Activity
import me.scraplesh.module.core.HasCoordinator
import me.scraplesh.module.navigation.Coordinator

fun provideCoordinator(activity: Activity): Coordinator =
  (activity as? HasCoordinator)?.coordinator
    ?: throw IllegalStateException("The activity you have passed does not implement HasCoordinator")