package navigation.redirects

import data.storage.LocalStorage
import navigation.FullPath
import navigation.Navigation

class LoggedInRedirect(
  private val localStorage: LocalStorage,
  private val navigation: Navigation,
) : Redirect {
  override val name = "logged-in-redirect"

  override suspend fun handle(): Boolean {
    val redirectPath = localStorage.getString(KEY_REDIRECT_PATH)
      ?: return false

    navigation.navigateTo(FullPath(redirectPath))
    localStorage.remove(KEY_REDIRECT_PATH)
    return true

  }

  suspend fun prepare(path: FullPath) {
    localStorage.putString(KEY_REDIRECT_PATH, path.value)
  }

  companion object {
    const val KEY_REDIRECT_PATH = "logged-in-redirect.fullpath"
  }
}