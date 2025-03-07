package bg.enterprise.parent_app.repository.write;

import bg.enterprise.parent_app.model.entity.Notification;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByParentId(Long id);
    List<Notification> findByChildId(Long id);
}
