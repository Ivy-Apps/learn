package ivy.learn.util

import arrow.core.Either
import arrow.core.raise.catch
import arrow.core.right
import kotlinx.io.bytestring.decodeToByteString
import kotlinx.io.bytestring.decodeToString
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class Base64Util {
    @OptIn(ExperimentalEncodingApi::class)
    fun decode(
        textBase64: String,
    ): Either<String, String> = catch({
        Base64.withPadding(Base64.PaddingOption.ABSENT_OPTIONAL)
            .decodeToByteString(textBase64).decodeToString()
            .right()
    }) { e ->
        Either.Left("Base64 decode of '$textBase64' failed because $e")
    }
}