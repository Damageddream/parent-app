package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DosageLogDto extends AuditDto {
    private float dosageGiven;
    private Instant administeredAt;
    private String notes;
}
