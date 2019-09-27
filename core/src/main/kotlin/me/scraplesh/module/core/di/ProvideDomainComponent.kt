package me.scraplesh.module.core.di

import android.content.Context

fun provideDomainComponent(context: Context): DomainComponent =
  (context.applicationContext as? HasDomainComponent)?.domainComponent
    ?: throw IllegalStateException("The application context you have passed does not implement HasDomainComponent")