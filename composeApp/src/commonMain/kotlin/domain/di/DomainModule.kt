package domain.di

import domain.GoogleAuthenticationUseCase
import ivy.di.Di
import ivy.di.autowire.autoWire

object DomainModule : Di.Module {
    override fun init() = Di.appScope {
        autoWire(::GoogleAuthenticationUseCase)
    }
}