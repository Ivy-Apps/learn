package di

import AppConfiguration
import ivy.data.HerokuServerUrlProvider
import ivy.data.LocalServerUrlProvider
import ivy.data.ServerUrlProvider
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.Di.singleton
import ivy.di.autowire.autoWire
import ivy.di.autowire.autoWireSingleton
import navigation.AccessControl
import navigation.Navigation
import navigation.redirects.GoogleAuthRedirect
import navigation.redirects.LoggedInRedirect
import navigation.redirects.LoggedOutUserRedirect
import navigation.systemNavigation
import ui.Toaster
import util.*

object AppModule : Di.Module {

  override fun init() {
    Di.appScope {
      singleton { initAppScope() }
      singleton { systemNavigation() }
      autoWireSingleton(::Navigation)
      autoWireSingleton(::AppConfiguration)
      register<DispatchersProvider> { DispatchersProviderImpl() }
      register<ServerUrlProvider> {
        if (Di.get<AppConfiguration>().useLocalServer) {
          Di.get<LocalServerUrlProvider>()
        } else {
          Di.get<HerokuServerUrlProvider>()
        }
      }
      autoWireSingleton(::Toaster)
      autoWire(::Logger)
      autoWire(::GoogleAuthRedirect)
      autoWire(::LoggedOutUserRedirect)
      autoWire(::LoggedInRedirect)
      autoWire(::TimeProvider)
      autoWire(::AccessControl)
    }
  }
}

inline fun <reified Base : Any, reified Impl : Base, reified Fake : Base> Di.Scope.bindWithFake() {
  register<Base> {
    if (Di.get<AppConfiguration>().fakesEnabled) {
      Di.get<Fake>()
    } else {
      Di.get<Impl>()
    }
  }
}