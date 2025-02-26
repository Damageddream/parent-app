package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChronicConditionDto extends AuditDto {
    private String name;
    private String description;
    private String instructions;
    private String notes;
    private LocalDate eventStart;
    private LocalDate eventEnd;
}