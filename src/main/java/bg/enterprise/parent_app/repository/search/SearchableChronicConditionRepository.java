package bg.enterprise.parent_app.repository.search;

import bg.enterprise.parent_app.model.entity.ChronicCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SearchableChronicConditionRepository extends JpaRepository<ChronicCondition, Long> {
    @Query("""
            SELECT cc FROM ChronicCondition cc
            LEFT JOIN cc.child ch
            WHERE (:name IS NULL OR cc.name = :name)
              AND (:childFirstName IS NULL OR ch.firstName = :childFirstName)
              AND (:description IS NULL OR cc.description = :description)
              AND (cast(:startDate as date) IS NULL OR cc.eventStart >= :startDate)
              AND (cast(:endDate as date) IS NULL OR cast(cc.eventEnd as date) IS NULL OR cc.eventEnd <= :endDate)
            ORDER BY cc.creationDate DESC
            """)
    List<ChronicCondition> searchBy(
            String name,
            String childFirstName,
            String description,
            LocalDate startDate,
            LocalDate endDate
    );
}
