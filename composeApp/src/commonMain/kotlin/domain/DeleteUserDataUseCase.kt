package domain

import arrow.core.Either
import data.UserRepository
import navigation.Navigation
import ui.Toaster
import ui.screen.intro.IntroScreen
import util.Logger

class DeleteUserDataUseCase(
    private val userRepository: UserRepository,
    private val navigation: Navigation,
    private val toaster: Toaster,
    private val sessionManager: SessionManager,
    private val logger: Logger,
) {
    suspend fun execute(): Either<String, Unit> = userRepository.deleteUserData()
        .onLeft { errMsg ->
            logger.error("Failed to delete user data: $errMsg")
            toaster.showToast(
                msg = "Failed to delete account data: $errMsg"
            )
        }
        .onRight {
            logger.info("User data deleted.")
            sessionManager.logout()
            navigation.replaceWith(IntroScreen())
            toaster.showToast(msg = "Account data deleted")
        }
}