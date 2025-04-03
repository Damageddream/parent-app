package bg.enterprise.parent_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@WithMockUser()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class IntegrationSpec {

    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private DataSource dataSource;

    private static boolean dataInitialized = false;

    protected Long testChildId;
    protected Long testParentId;
    protected Long testMedicationId;


    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        PostgreSQLContainer<?> postgresContainer = SharedPostgresContainer.getInstance();
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @BeforeAll
    void loadTestData() throws Exception {
        if (dataInitialized) {
            return;
        }
        dataInitialized = true;
        String sqlScriptPath = "src/test/java/resources/scripts/init.sql";

        try (Connection conn = dataSource.getConnection()) {
            String sql = Files.readString(Path.of(sqlScriptPath));
            for (String statement : sql.split(";")) {
                if (!statement.isBlank()) {
                    try (PreparedStatement ps = conn.prepareStatement(statement)) {
                        ps.execute();
                    }
                }
            }

            try (PreparedStatement parentQuery = conn.prepareStatement("SELECT id FROM parent WHERE email = 'parent@test.com'");
                 PreparedStatement childQuery = conn.prepareStatement("SELECT id FROM child WHERE first_name = 'Child' AND last_name = 'Test'");
                 PreparedStatement medicationQuery = conn.prepareStatement("SELECT id FROM medication WHERE name = 'Paracetamol'")) {

                var parentRs = parentQuery.executeQuery();
                if (parentRs.next()) {
                    testParentId = parentRs.getLong("id");
                }

                var childRs = childQuery.executeQuery();
                if (childRs.next()) {
                    testChildId = childRs.getLong("id");
                }

                var medicationRs = medicationQuery.executeQuery();
                if (medicationRs.next()) {
                    testMedicationId = medicationRs.getLong("id");
                }
            }
        }

    }

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
}