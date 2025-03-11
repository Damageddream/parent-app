package bg.enterprise.parent_app.repository.search;

import bg.enterprise.parent_app.model.entity.Illness;
import bg.enterprise.parent_app.model.type.IllnessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SearchableIllnessRepository extends JpaRepository<Illness, Long> {

    @Query("""
            SELECT e FROM Illness e
            LEFT JOIN e.child ch
            WHERE (:name IS NULL OR e.name = :name)
              AND (:childFirstName IS NULL OR ch.firstName = :childFirstName)
              AND (:illnessType IS NULL OR e.illnessType = :illnessType)
              AND (:description IS NULL OR e.description = :description)
              AND (cast(:startDate as date) IS NULL OR e.eventStart >= :startDate)
              AND (cast(:endDate as date) IS NULL OR cast(e.eventEnd as date) IS NULL OR e.eventEnd <= :endDate)
            ORDER BY e.creationDate DESC
            """)
    List<Illness> searchBy(
            String name,
            String childFirstName,
            IllnessType illnessType,
            String description,
            LocalDate startDate,
            LocalDate endDate);
}
