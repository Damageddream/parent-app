package bg.enterprise.parent_app.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotificationDto extends AuditDto {
    private Long parentId;
    private Long childId;
    private String message;
    private boolean read;
}
