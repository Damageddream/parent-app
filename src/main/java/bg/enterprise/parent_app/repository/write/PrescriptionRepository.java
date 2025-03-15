package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByChildId(Long id);
}
