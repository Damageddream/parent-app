package bg.enterprise.parent_app.model.dto;

import bg.enterprise.parent_app.model.type.InjuryType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InjuryDto extends AuditDto {
    private String name;
    private InjuryType type;
    private String description;
    private Long childId;
    private LocalDate eventStart;
    private LocalDate eventEnd;
}