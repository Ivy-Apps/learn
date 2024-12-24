import io.ktor.client.*
import io.ktor.client.engine.js.*
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Audio
import org.w3c.dom.HTMLMetaElement
import org.w3c.dom.url.URLSearchParams

class JsPlatform : Platform {
  override val name: String = "web"

  override fun log(level: LogLevel, msg: String) {
    console.log("${level.name.uppercase()}: $msg")
  }

  override fun httpClient(
    config: HttpClientConfig<*>.() -> Unit
  ): HttpClient = HttpClient(Js) {
    config(this)
  }

  override fun playSound(soundUrl: String) {
    val audio = Audio(soundUrl)
    audio.play()
  }

  override fun getUrlParam(key: String): String? {
    val urlParams = URLSearchParams(window.location.search)
    return urlParams.get(key)
  }

  override fun setSocialPreview(
    title: String,
    description: String,
    imageUrl: String,
    pageUrl: String
  ) {
    // Helper function to update or create a meta tag
    fun setMetaTag(property: String, content: String) {
      val metaTag = document.querySelector("meta[property='$property']") as? HTMLMetaElement
      if (metaTag != null) {
        metaTag.content = content
      } else {
        val newMetaTag = document.createElement("meta") as HTMLMetaElement
        newMetaTag.setAttribute("property", property)
        newMetaTag.content = content
        document.head?.appendChild(newMetaTag)
      }
    }

    // Update Open Graph meta tags (used by LinkedIn, Reddit, Facebook, etc.)
    setMetaTag("og:title", title)
    setMetaTag("og:description", description)
    setMetaTag("og:image", imageUrl)
    setMetaTag("og:url", pageUrl)

    // Twitter-specific tags for improved preview
    fun setTwitterMetaTag(name: String, content: String) {
      val metaTag = document.querySelector("meta[name='$name']") as? HTMLMetaElement
      if (metaTag != null) {
        metaTag.content = content
      } else {
        val newMetaTag = document.createElement("meta") as HTMLMetaElement
        newMetaTag.setAttribute("name", name)
        newMetaTag.content = content
        document.head?.appendChild(newMetaTag)
      }
    }

    setTwitterMetaTag("twitter:card", "summary_large_image") // Ensure large preview cards
    setTwitterMetaTag("twitter:title", title)
    setTwitterMetaTag("twitter:description", description)
    setTwitterMetaTag("twitter:image", imageUrl)
  }
}

actual fun platform(): Platform = JsPlatform()