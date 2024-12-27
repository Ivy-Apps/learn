import di.AppModule
import io.kotest.matchers.shouldBe
import ivy.di.Di
import kotlin.test.BeforeTest
import kotlin.test.Test

class AppConfigurationTest {
  @BeforeTest
  fun setup() {
    Di.reset()
    Di.init(AppModule)
  }

  @Test
  fun fakes_should_be_disabled() {
    // Given
    val appConfiguration = Di.get<AppConfiguration>()

    // When
    val fakesEnabled = appConfiguration.fakesEnabled

    // Then
    fakesEnabled shouldBe false
  }

  @Test
  fun should_not_use_local_server() {
    // Given
    val appConfiguration = Di.get<AppConfiguration>()

    // When
    val userLocalServer = appConfiguration.useLocalServer

    // Then
    userLocalServer shouldBe false
  }
}