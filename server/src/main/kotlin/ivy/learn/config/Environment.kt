package ivy.learn.config

import arrow.core.Either
import arrow.core.right

interface Environment {
    fun getVariable(name: String): Either<String, String>
}

class EnvironmentImpl : Environment {
    override fun getVariable(name: String): Either<String, String> =
        System.getenv(name)?.right()
            ?: Either.Left("Environment variable $name not found")
}