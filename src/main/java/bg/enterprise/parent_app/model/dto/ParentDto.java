package bg.enterprise.parent_app.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ParentDto extends AuditDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private List<ChildDto> children;
    private List<DosageLogDto> dosageLogs;
    private List<NotificationDto> notifications;
}
