package bg.enterprise.parent_app.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class EventDto extends AuditDto {
    private LocalDate eventStart;
    private LocalDate eventEnd;
}
