package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ChildDto extends AuditDto {
    private Long parentId;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String notes;
    private List<PrescriptionDto> prescriptions;
    private List<NotificationDto> notifications;
}
