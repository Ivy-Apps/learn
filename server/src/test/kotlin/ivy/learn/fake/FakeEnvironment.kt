package ivy.learn.fake

import arrow.core.Either
import arrow.core.right
import ivy.learn.config.Environment

class FakeEnvironment : Environment {
    private val variables = mutableMapOf<String, String>()

    override fun getVariable(name: String): Either<String, String> =
        variables[name]?.right() ?: Either.Left("Environment variable $name not found")

    fun setVariable(name: String, value: String) {
        variables[name] = value
    }

    fun removeVariable(name: String) {
        variables.remove(name)
    }

    fun clear() {
        variables.clear()
    }
}