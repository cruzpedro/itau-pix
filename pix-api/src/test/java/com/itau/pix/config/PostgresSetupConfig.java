package com.itau.pix.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class PostgresSetupConfig {

    private static final String POSTGRES_DATABASE_NAME = "pixdb_test";
    private static final Integer POSTGRES_PORT = 5432;
    private static final String POSTGRES_USERNAME = "postgres";
    private static final String POSTGRES_PASSWORD = "postgres_test";
    private static final DockerImageName POSTGRES_IMAGE = DockerImageName.parse("postgres:14.4");

    private static final PostgreSQLContainer POSTGRES_CONTAINER = (PostgreSQLContainer) new PostgreSQLContainer(POSTGRES_IMAGE)
            .withDatabaseName(POSTGRES_DATABASE_NAME)
            .withUsername(POSTGRES_USERNAME)
            .withPassword(POSTGRES_PASSWORD)
            .withExposedPorts(POSTGRES_PORT);

    public static void initContainer() {
        POSTGRES_CONTAINER.start();
        POSTGRES_CONTAINER.waitingFor(Wait.forHealthcheck());

        System.setProperty("spring.datasource.url", POSTGRES_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTGRES_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTGRES_CONTAINER.getPassword());

        final ClassicConfiguration configuration = new ClassicConfiguration();
        configuration.setDataSource(POSTGRES_CONTAINER.getJdbcUrl(), POSTGRES_CONTAINER.getUsername(), POSTGRES_CONTAINER.getPassword());
        configuration.setLocations(new Location("classpath:db/migration"));

        final Flyway flyway = new Flyway(configuration);
        flyway.migrate();
    }

}
