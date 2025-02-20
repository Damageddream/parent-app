package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class MedicationOpenLogDto extends AuditDto {
    private Instant openedAt;
    private String notes;
}
