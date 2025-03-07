package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.Injury;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InjuryRepository extends JpaRepository<Injury, Long> {
    List<Injury> findByChildId(Long id);
}
