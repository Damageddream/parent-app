package bg.enterprise.parent_app.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class AuditDto {
    private Instant creationDate;
    private String createdBy;
    private Instant updateDate;
    private String updatedBy;
}
