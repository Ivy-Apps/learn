package ivy.data

interface ServerUrlProvider {
    val serverUrl: String
}

class ServerUrlProviderImpl : ServerUrlProvider {
    override val serverUrl: String = "https://ivy-learn-0c3c19230895.herokuapp.com"
}