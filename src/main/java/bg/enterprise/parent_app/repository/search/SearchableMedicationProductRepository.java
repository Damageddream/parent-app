package bg.enterprise.parent_app.repository.search;

import bg.enterprise.parent_app.model.entity.MedicationProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchableMedicationProductRepository extends JpaRepository<MedicationProduct, Long> {
}