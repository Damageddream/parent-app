package bg.enterprise.parent_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ParentAppApplication {
	double asd = 2.9;

	public static void main(String[] args) {
		SpringApplication.run(ParentAppApplication.class, args);
	}

}
