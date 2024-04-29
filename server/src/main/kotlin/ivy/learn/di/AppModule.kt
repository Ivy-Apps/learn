package ivy.learn.di

import ivy.di.Di
import ivy.di.Di.singleton
import ivy.di.DiModule
import ivy.learn.LearnServer

class AppModule(private val devMode: Boolean) : DiModule {
    override fun init() = Di.appScope {
        singleton {
            LearnServer(
                database = Di.get(),
                devMode = devMode,
            )
        }
    }
}