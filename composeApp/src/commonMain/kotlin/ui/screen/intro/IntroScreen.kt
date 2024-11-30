package ui.screen.intro

import androidx.compose.runtime.Composable
import arrow.core.Option
import arrow.core.raise.option
import ivy.di.Di
import ivy.di.autowire.autoWire
import navigation.Route
import navigation.Router
import navigation.Screen
import ui.screen.intro.composable.IntroContent

object IntroRouter : Router<IntroScreen> {
    const val PATH = "intro"

    override fun fromRoute(route: Route): Option<IntroScreen> = option {
        ensure(route.path == PATH || route.path == "")
        IntroScreen()
    }

    override fun toRoute(screen: IntroScreen): Route {
        return Route(PATH)
    }
}

class IntroScreen : Screen() {
    override fun toRoute(): Route = IntroRouter.toRoute(this)

    override fun Di.Scope.onDi() {
        autoWire(::IntroViewModel)
    }

    private val viewModel: IntroViewModel by lazy { Di.get() }

    @Composable
    override fun Content() {
        IntroContent(
            viewState = viewModel.viewState(),
            onEvent = viewModel::onEvent
        )
    }
}