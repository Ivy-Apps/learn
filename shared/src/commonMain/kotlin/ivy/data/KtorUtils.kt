package ivy.data

import IvyConstants
import io.ktor.client.request.*
import io.ktor.http.*
import ivy.model.auth.SessionToken

fun HttpRequestBuilder.headerSessionToken(session: SessionToken) {
    header(IvyConstants.HEADER_SESSION_TOKEN, session.value)
}

fun Headers.sessionToken(): SessionToken? {
    return this[IvyConstants.HEADER_SESSION_TOKEN]?.let(::SessionToken)
}
