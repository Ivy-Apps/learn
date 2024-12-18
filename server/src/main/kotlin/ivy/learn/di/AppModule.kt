package ivy.learn.di

import ivy.di.Di
import ivy.di.Di.bind
import ivy.di.Di.register
import ivy.di.autowire.autoWire
import ivy.di.autowire.autoWireSingleton
import ivy.learn.LearnServer
import ivy.learn.ServerMode
import ivy.learn.config.Environment
import ivy.learn.config.EnvironmentImpl
import ivy.learn.config.ServerConfigurationProvider
import ivy.learn.util.Base64Util
import ivy.learn.util.Crypto
import ivy.learn.util.TimeProvider

class AppModule(private val devMode: Boolean) : Di.Module {
    override fun init() = Di.appScope {
        register { ServerMode(devMode) }
        autoWire(::EnvironmentImpl)
        bind<Environment, EnvironmentImpl>()
        autoWire(::ServerConfigurationProvider)
        autoWireSingleton(::LearnServer)
        autoWire(::Crypto)
        autoWire(::TimeProvider)
        autoWire(::Base64Util)
    }
}