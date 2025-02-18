package bg.enterprise.parent_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ParentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParentAppApplication.class, args);
	}

}
