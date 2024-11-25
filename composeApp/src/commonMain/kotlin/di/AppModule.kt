package di

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
            register<DispatchersProvider> { DispatchersProviderImpl() }
        }
    }
}