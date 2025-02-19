package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDto extends AuditDto {
    private String name;
    private String brand;
    private String description;
    private String dosageForm;
    private String instructions;
}
