package ivy.learn.util

import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.string.shouldNotBeBlank
import org.junit.Before
import org.junit.Test

class CryptoTest {

    private lateinit var crypto: Crypto

    @Before
    fun setup() {
        crypto = Crypto()
    }

    @Test
    fun `generates valid token`() {
        // When
        val token = crypto.generateSecureToken()

        // Then
        token.shouldNotBeBlank()
        token.length shouldBeInRange 20..128
    }

    @Test
    fun `generates random tokens`() {
        // When
        val token1 = crypto.generateSecureToken()
        val token2 = crypto.generateSecureToken()

        // Then
        token1 shouldNotBeEqual token2
    }
}