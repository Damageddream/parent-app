package bg.enterprise.parent_app.repository.search;

import bg.enterprise.parent_app.model.entity.Illness;
import bg.enterprise.parent_app.model.type.IllnessType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface SearchableIllnessRepository extends ListPagingAndSortingRepository<Illness, Long> {

    @Query(
            """
                    SELECT e FROM Illness e
                    LEFT JOIN e.child ch
                    WHERE (:name IS NULL OR e.name = :name)
                      AND (:childFirstName IS NULL OR ch.firstName = :childFirstName)
                      AND (:illnessType IS NULL OR e.illnessType = :illnessType)
                      AND (:description IS NULL OR e.description = :description)
                      AND (e.eventStart >= COALESCE(:eventStartFrom, e.eventStart))
                      AND (e.eventStart <= COALESCE(:eventStartTo, e.eventStart))
                      AND (e.eventEnd >= COALESCE(:eventEndFrom, e.eventEnd))
                      AND (e.eventEnd <= COALESCE(:eventEndTo, e.eventEnd))
                    ORDER BY e.creationDate DESC
                    """)
    List<Illness> searchBy(
            String name,
            String childFirstName,
            IllnessType illnessType,
            String description,
            LocalDate eventStartFrom,
            LocalDate eventStartTo,
            LocalDate eventEndFrom,
            LocalDate eventEndTo);
}
