package ivy.learn.di

import ivy.di.Di
import ivy.di.Di.register
import ivy.di.Di.singleton
import ivy.di.DiModule
import ivy.learn.Environment
import ivy.learn.EnvironmentImpl
import ivy.learn.LearnServer
import ivy.learn.ServerConfigurationProvider

class AppModule(private val devMode: Boolean) : DiModule {
    override fun init() = Di.appScope {
        register<Environment> { EnvironmentImpl() }
        register { ServerConfigurationProvider(Di.get()) }
        singleton {
            LearnServer(Di.get(), Di.get())
        }
    }
}