package no.navneet.spacegame.integrationtest

import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.client.JerseyClientConfiguration
import io.dropwizard.testing.DropwizardTestSupport
import io.dropwizard.util.Duration
import no.navneet.spacegame.SpaceGameApplication
import no.navneet.spacegame.SpaceGameConfiguration
import java.io.File
import javax.ws.rs.client.Client

object TestApp {

    val httpClient: Client
    val appPort: Int
    val adminPort: Int
    private var DROPWIZARD_TEST_SUPPORT: DropwizardTestSupport<SpaceGameConfiguration>
    private val CONFIG_PATH =
        File("src/main/resources/config.yml").toPath().toAbsolutePath().toString()

    init {

        DROPWIZARD_TEST_SUPPORT =
            DropwizardTestSupport(
                SpaceGameApplication::class.java,
                CONFIG_PATH
            )
        DROPWIZARD_TEST_SUPPORT.before()
        appPort = DROPWIZARD_TEST_SUPPORT.localPort
        adminPort = DROPWIZARD_TEST_SUPPORT.adminPort

        // The default timeouts are 500ms which is a bit too low for the integration tests
        val jerseyClientConfiguration = JerseyClientConfiguration()
        jerseyClientConfiguration.timeout = Duration.seconds(
            30000
        )
        httpClient =
            JerseyClientBuilder(DROPWIZARD_TEST_SUPPORT.environment)
                .using(jerseyClientConfiguration)
                .build("Test client")
    }
}
