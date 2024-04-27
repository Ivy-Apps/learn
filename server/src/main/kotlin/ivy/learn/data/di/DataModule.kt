package ivy.learn.data.di

import ivy.di.Di
import ivy.di.Di.register
import ivy.di.DiModule
import ivy.learn.data.database.DbConfigProvider
import ivy.learn.data.database.ExposedDatabase

object DataModule : DiModule {
    override fun init() = Di.appScope {
        register { DbConfigProvider() }
        register { ExposedDatabase(Di.get()) }
    }
}