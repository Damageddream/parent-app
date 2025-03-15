package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
