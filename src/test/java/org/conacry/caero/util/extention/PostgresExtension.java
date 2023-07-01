package org.conacry.caero.util.extention;

import org.junit.jupiter.api.extension.Extension;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresExtension implements Extension {

    static {
        PostgreSQLContainer postgresContainer = new PostgreSQLContainer<>("postgres:15.3-alpine")
                .withDatabaseName("local_pg_db");
        postgresContainer.start();

        System.setProperty("spring.datasource.dataSourceProperties.serverName", postgresContainer.getHost());
        System.setProperty("spring.datasource.dataSourceProperties.portNumber", String.valueOf(postgresContainer.getFirstMappedPort()));
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
        System.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
    }
}
