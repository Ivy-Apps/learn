package domain

import Platform
import data.SoundRepository

class SoundUseCase(
    private val soundRepository: SoundRepository,
    private val platform: Platform
) {
    suspend fun playSound(sound: String) {
        if (soundRepository.getSoundEnabled()) {
            platform.playSound(sound)
        }
    }
}