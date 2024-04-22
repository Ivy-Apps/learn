package di

import ivy.di.Di
import ivy.di.Di.singleton
import ivy.di.DiModule
import ui.navigation.Navigation

object AppModule : DiModule {

    override fun init() {
        Di.appScope {
            singleton { Navigation() }
        }
    }
}