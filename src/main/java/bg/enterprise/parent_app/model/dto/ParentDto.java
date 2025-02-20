package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParentDto extends AuditDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private List<ChildDto> children;
    private List<DosageLogDto> dosageLogs;
    private List<MedicationOpenLogDto> medicationOpenLogs;
    private List<NotificationDto> notifications;
}
