package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.Illness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllnessRepository extends JpaRepository<Illness, Long> {
}
