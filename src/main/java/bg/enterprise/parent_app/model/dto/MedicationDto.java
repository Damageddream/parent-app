package bg.enterprise.parent_app.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class MedicationDto extends AuditDto {
    private String name;
    private String brand;
    private String description;
    private String dosageForm;
    private LocalDate openDate;
    private String instructions;
}
