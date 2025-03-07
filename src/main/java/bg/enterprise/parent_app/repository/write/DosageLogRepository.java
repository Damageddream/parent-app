package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.DosageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DosageLogRepository extends JpaRepository<DosageLog, Long> {
}
