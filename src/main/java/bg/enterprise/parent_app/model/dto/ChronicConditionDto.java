package bg.enterprise.parent_app.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChronicConditionDto extends EventDto {
    private String name;
    private String description;
    private String instructions;
    private String notes;
}