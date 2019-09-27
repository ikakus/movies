package me.scraplesh.movies.navigation

import io.reactivex.functions.Consumer

interface Coordinator : Consumer<NavigationEvent>

interface NavigationEvent