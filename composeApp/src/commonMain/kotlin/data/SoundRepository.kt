package data

import data.storage.LocalStorage

class SoundRepository(
    private val localStorage: LocalStorage
) {
    suspend fun getSoundEnabled(): Boolean {
        return localStorage.getBoolean(key = SOUND_ENABLED_KEY) ?: true
    }

    suspend fun setSoundEnabled(enabled: Boolean) {
        localStorage.putBoolean(
            key = SOUND_ENABLED_KEY,
            value = enabled
        )
    }

    companion object {
        const val SOUND_ENABLED_KEY = "sounds_enabled"
    }
}