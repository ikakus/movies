package me.scraplesh.module.navigation

import android.app.Activity

fun provideCoordinator(activity: Activity): Coordinator =
  (activity as? HasCoordinator)?.coordinator
    ?: throw IllegalStateException("The activity you have passed does not implement HasCoordinator")