package ivy.learn.util

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class Base64UtilTest {
    private lateinit var base64: Base64Util

    @Before
    fun setup() {
        base64 = Base64Util()
    }

    enum class ValidBase64TestCase(
        val encoded: String,
        val expectedDecoded: String,
    ) {
        PADDED(
            encoded = "SGVsbG8sIEJhc2U2NCE=",
            expectedDecoded = "Hello, Base64!",
        ),
        NOT_PADDED(
            encoded = "SGVsbG8sIEJhc2U2NCE",
            expectedDecoded = "Hello, Base64!"
        )
    }

    @Test
    fun `decodes valid base64`(
        @TestParameter testCase: ValidBase64TestCase,
    ) {
        // Given
        val encoded = testCase.encoded

        // When
        val decoded = base64.decode(encoded)

        // Then
        decoded.shouldBeRight() shouldBe testCase.expectedDecoded
    }

    @Test
    fun `fails to decode invalid base64`() {
        // Given
        val encoded = "Hello, Base64!"

        // When
        val decoded = base64.decode(encoded)

        // Then
        decoded.shouldBeLeft()
    }
}