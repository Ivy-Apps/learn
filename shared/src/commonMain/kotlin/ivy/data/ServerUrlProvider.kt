package ivy.data

interface ServerUrlProvider {
    val serverUrl: String
}

class HerokuServerUrlProvider : ServerUrlProvider {
    override val serverUrl: String = "https://ivy-learn-0c3c19230895.herokuapp.com"
}

class LocalServerUrlProvider : ServerUrlProvider {
    override val serverUrl: String
        get() = "http://localhost:8081"
}