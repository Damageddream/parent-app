package bg.enterprise.parent_app.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Return a default value ("system") when there is no authenticated user.
        return Optional.of("system");
    }
}