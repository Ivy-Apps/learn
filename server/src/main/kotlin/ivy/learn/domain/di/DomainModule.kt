package ivy.learn.domain.di

import ivy.di.Di
import ivy.di.autowire.autoWire
import ivy.learn.domain.auth.AuthenticationService
import ivy.learn.domain.auth.GoogleOAuthUseCase

object DomainModule : Di.Module {
    override fun init() = Di.appScope {
        autoWire(::AuthenticationService)
        autoWire(::GoogleOAuthUseCase)
    }
}