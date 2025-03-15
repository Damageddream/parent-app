package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    boolean existsByEmail(String email);
}
