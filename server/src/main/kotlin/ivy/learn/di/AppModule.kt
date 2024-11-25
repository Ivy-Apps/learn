package ivy.learn.di

import ivy.di.Di
import ivy.di.Di.register
import ivy.di.autowire.autoWire
import ivy.di.autowire.autoWireSingleton
import ivy.learn.Environment
import ivy.learn.EnvironmentImpl
import ivy.learn.LearnServer
import ivy.learn.ServerConfigurationProvider

class AppModule(private val devMode: Boolean) : Di.Module {
    override fun init() = Di.appScope {
        register<Environment> { EnvironmentImpl() }
        autoWire(::ServerConfigurationProvider)
        autoWireSingleton(::LearnServer)
    }
}