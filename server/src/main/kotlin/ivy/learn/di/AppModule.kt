package ivy.learn.di

import ivy.di.Di
import ivy.di.Di.singleton
import ivy.di.DiModule
import ivy.learn.LearnServer

object AppModule : DiModule {
    override fun init() = Di.appScope {
        singleton { LearnServer(Di.get()) }
    }
}