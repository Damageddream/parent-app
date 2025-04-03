package bg.enterprise.parent_app.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class DosageLogDto extends AuditDto {
    private Long dosageGiven;
    private Instant administeredAt;
    private String notes;
}
