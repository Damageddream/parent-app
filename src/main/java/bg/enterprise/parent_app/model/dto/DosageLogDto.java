package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class DosageLogDto extends AuditDto {
    private BigDecimal dosageGiven;
    private Instant administeredAt;
    private String notes;
}
