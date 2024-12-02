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
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import navigation.Navigation
import navigation.systemNavigation
import ui.Toaster
import util.DispatchersProvider
import util.DispatchersProviderImpl
import util.Logger

object AppModule : Di.Module {

    override fun init() {
        Di.appScope {
            register { systemNavigation() }
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
            singleton { CoroutineScope(Dispatchers.Main + CoroutineName("App")) }
            autoWireSingleton(::Toaster)
            autoWire(::Logger)
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