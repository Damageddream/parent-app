package bg.enterprise.parent_app.model.dto;

import bg.enterprise.parent_app.model.type.IllnessType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IllnessDto extends AuditDto {
    private String name;
    private IllnessType type;
    private String description;
    private LocalDate eventStart;
    private LocalDate eventEnd;
}