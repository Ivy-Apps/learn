package di

import AppConfiguration
import ivy.data.HerokuServerUrlProvider
import ivy.data.LocalServerUrlProvider
import ivy.data.ServerUrlProvider
import ivy.di.Di
import ivy.di.Di.register
import ivy.di.autowire.autoWireSingleton
import systemNavigation
import ui.navigation.Navigation
import util.DispatchersProvider
import util.DispatchersProviderImpl

object AppModule : Di.Module {

    override fun init() {
        Di.appScope {
            register { systemNavigation() }
            autoWireSingleton(::Navigation)
            autoWireSingleton(::AppConfiguration)
            register<DispatchersProvider> { DispatchersProviderImpl() }
            bindWithFake<ServerUrlProvider, HerokuServerUrlProvider, LocalServerUrlProvider>()
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