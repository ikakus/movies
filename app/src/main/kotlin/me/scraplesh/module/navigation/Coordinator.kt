package me.scraplesh.module.navigation

import io.reactivex.functions.Consumer

interface Coordinator : Consumer<NavigationEvent>

interface NavigationEvent