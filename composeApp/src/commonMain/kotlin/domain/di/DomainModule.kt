package domain.di

import di.bindWithFake
import domain.GoogleAuthenticationUseCase
import domain.GoogleAuthenticationUseCaseImpl
import domain.SessionManager
import domain.fake.FakeGoogleAuthenticationUseCase
import ivy.di.Di
import ivy.di.autowire.autoWire
import ivy.di.autowire.autoWireSingleton

object DomainModule : Di.Module {
    override fun init() = Di.appScope {
        autoWire(::GoogleAuthenticationUseCaseImpl)
        autoWire(::FakeGoogleAuthenticationUseCase)
        bindWithFake<GoogleAuthenticationUseCase, GoogleAuthenticationUseCaseImpl, FakeGoogleAuthenticationUseCase>()
        autoWireSingleton(::SessionManager)
    }
}