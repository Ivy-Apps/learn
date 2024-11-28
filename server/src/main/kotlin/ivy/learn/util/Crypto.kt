package ivy.learn.util

import java.security.SecureRandom
import java.util.*

class Crypto {
    fun generateSecureToken(byteLength: Int = 32): String {
        val secureRandom = SecureRandom()
        val tokenBytes = ByteArray(byteLength)
        secureRandom.nextBytes(tokenBytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes)
    }
}