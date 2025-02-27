package bg.enterprise.parent_app.repository.search;

import bg.enterprise.parent_app.model.entity.Illness;
import bg.enterprise.parent_app.model.type.IllnessType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface SearchableIllnessRepository extends ListPagingAndSortingRepository<Illness, Long> {

    @Query("""
            SELECT e FROM Illness e
            LEFT JOIN e.child ch
            WHERE (:name IS NULL OR e.name = :name)
              AND (:childFirstName IS NULL OR ch.firstName = :childFirstName)
              AND (:illnessType IS NULL OR e.illnessType = :illnessType)
              AND (:description IS NULL OR e.description = :description)
              AND (:startDate IS NULL OR e.eventStart >= :startDate)
              AND (:endDate IS NULL OR e.eventEnd <= :endDate)
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
