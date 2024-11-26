package ivy.data

interface ServerUrlProvider {
    val serverUrl: String
}

class HerokuServerUrlProvider : ServerUrlProvider {
    override val serverUrl: String = "https://api.ivylearn.app"
}

class LocalServerUrlProvider : ServerUrlProvider {
    override val serverUrl: String
        get() = "http://localhost:8081"
}