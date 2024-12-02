package domain

import arrow.core.Either
import data.UserRepository
import navigation.Navigation
import ui.Toaster
import ui.screen.intro.IntroScreen

class DeleteUserDataUseCase(
    private val userRepository: UserRepository,
    private val navigation: Navigation,
    private val toaster: Toaster,
) {
    suspend fun execute(): Either<String, Unit> = userRepository.deleteUserData()
        .onLeft { errMsg ->
            toaster.showToast(
                msg = "Failed to delete account data: $errMsg"
            )
        }
        .onRight {
            navigation.replaceWith(IntroScreen())
            toaster.showToast(msg = "Account data deleted.")
        }
}