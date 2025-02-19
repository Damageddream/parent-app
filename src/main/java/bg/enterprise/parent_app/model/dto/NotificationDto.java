package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto extends AuditDto {
    private long parentId;
    private long childId;
    private String message;
    private boolean isRead;
}
