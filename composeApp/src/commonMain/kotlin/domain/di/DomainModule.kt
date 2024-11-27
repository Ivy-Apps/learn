package domain.di

import di.bindWithFake
import domain.GoogleAuthenticationUseCase
import domain.GoogleAuthenticationUseCaseImpl
import domain.fake.FakeGoogleAuthenticationUseCase
import ivy.di.Di
import ivy.di.autowire.autoWire

object DomainModule : Di.Module {
    override fun init() = Di.appScope {
        autoWire(::GoogleAuthenticationUseCaseImpl)
        autoWire(::FakeGoogleAuthenticationUseCase)
        bindWithFake<GoogleAuthenticationUseCase, GoogleAuthenticationUseCaseImpl, FakeGoogleAuthenticationUseCase>()
    }
}