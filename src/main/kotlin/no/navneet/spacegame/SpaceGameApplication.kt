package no.navneet.spacegame

import com.fasterxml.jackson.annotation.JsonInclude
import io.dropwizard.Application
import io.dropwizard.configuration.EnvironmentVariableSubstitutor
import io.dropwizard.configuration.SubstitutingSourceProvider
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.migrations.MigrationsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class SpaceGameApplication : Application<SpaceGameConfiguration>() {
    override fun getName(): String {
        return "SpaceGame"
    }

    override fun initialize(bootstrap: Bootstrap<SpaceGameConfiguration>) {
        super.initialize(bootstrap)
        bootstrap.configurationSourceProvider = SubstitutingSourceProvider(
            bootstrap.configurationSourceProvider, EnvironmentVariableSubstitutor()
        )
    }

    override fun run(
        configuration: SpaceGameConfiguration,
        environment: Environment
    ) {
        environment.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpaceGameApplication().run(*args)
        }
    }
}