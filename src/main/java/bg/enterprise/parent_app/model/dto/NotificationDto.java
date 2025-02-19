package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto extends AuditDto {
    private Long parentId;
    private Long childId;
    private String message;
    private Boolean isRead;
}
