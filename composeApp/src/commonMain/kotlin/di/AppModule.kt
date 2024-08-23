package di

import ivy.di.Di
import ivy.di.Di.register
import ivy.di.Di.singleton
import systemNavigation
import ui.navigation.Navigation
import util.DispatchersProvider
import util.DispatchersProviderImpl

object AppModule : Di.Module {

    override fun init() {
        Di.appScope {
            register { systemNavigation() }
            singleton { Navigation(Di.get()) }
            register<DispatchersProvider> { DispatchersProviderImpl() }
        }
    }
}