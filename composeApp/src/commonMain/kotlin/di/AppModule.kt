package di

import ivy.di.Di
import ivy.di.Di.register
import ivy.di.Di.singleton
import ivy.di.DiModule
import ui.navigation.Navigation
import util.DispatchersProvider
import util.DispatchersProviderImpl

object AppModule : DiModule {

    override fun init() {
        Di.appScope {
            singleton { Navigation() }
            register<DispatchersProvider> { DispatchersProviderImpl() }
        }
    }
}