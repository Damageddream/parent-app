package bg.enterprise.parent_app.repository.search;

import bg.enterprise.parent_app.model.entity.Injury;
import bg.enterprise.parent_app.model.type.InjuryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SearchableInjuryRepository extends JpaRepository<Injury, Long> {
    @Query("""
        SELECT i FROM Injury i
        LEFT JOIN i.child ch
        WHERE (:name IS NULL OR i.name = :name)
          AND (:childFirstName IS NULL OR ch.firstName = :childFirstName)
          AND (:injuryType IS NULL OR i.injuryType = :injuryType)
          AND (:description IS NULL OR i.description = :description)
          AND (cast(:startDate as date) IS NULL OR i.eventStart >= :startDate)
          AND (cast(:endDate as date) IS NULL OR cast(i.eventEnd as date) IS NULL OR i.eventEnd <= :endDate)
        ORDER BY i.creationDate DESC
        """)
    List<Injury> searchBy(
            String name,
            String childFirstName,
            InjuryType injuryType,
            String description,
            LocalDate startDate,
            LocalDate endDate
    );
}
