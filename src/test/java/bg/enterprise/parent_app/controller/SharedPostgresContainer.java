package bg.enterprise.parent_app.controller;

import org.testcontainers.containers.PostgreSQLContainer;

public class SharedPostgresContainer {
    private static final PostgreSQLContainer<?> INSTANCE =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("parent-app-db")
                    .withUsername("user")
                    .withPassword("pass");

    static {
        INSTANCE.start();
    }

    public static PostgreSQLContainer<?> getInstance() {
        return INSTANCE;
    }
}