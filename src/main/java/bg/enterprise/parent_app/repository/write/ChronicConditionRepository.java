package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.ChronicCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChronicConditionRepository extends JpaRepository<ChronicCondition, Long> {
    List<ChronicCondition> findByChildId(Long id);
}
