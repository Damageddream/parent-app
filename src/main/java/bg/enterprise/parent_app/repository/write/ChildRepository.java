package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
