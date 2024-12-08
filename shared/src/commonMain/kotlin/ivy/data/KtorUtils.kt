package ivy.data

import IvyConstants
import io.ktor.client.request.*
import ivy.model.auth.SessionToken

fun HttpRequestBuilder.headerSessionToken(session: SessionToken) {
    header(IvyConstants.HEADER_SESSION_TOKEN, session.value)
}
