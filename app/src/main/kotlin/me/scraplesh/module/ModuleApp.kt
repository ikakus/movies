package me.scraplesh.module

import android.app.Application
import me.scraplesh.module.core.DaggerDomainComponent
import me.scraplesh.module.core.DomainComponent
import me.scraplesh.module.core.DomainModule
import me.scraplesh.module.core.HasDomainComponent
import me.scraplesh.module.di.AppComponent
import me.scraplesh.module.di.AppModule
import me.scraplesh.module.di.DaggerAppComponent

class ModuleApp : Application(), HasDomainComponent {
  val appComponent: AppComponent = DaggerAppComponent.builder()
    .appModule(AppModule(this))
    .build()
  override val domainComponent: DomainComponent = DaggerDomainComponent.builder()
    .domainModule(DomainModule(appComponent.imdbWebApi()))
    .build()
}