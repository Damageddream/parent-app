package bg.enterprise.parent_app.model.dto;

import bg.enterprise.parent_app.model.type.InjuryType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InjuryDto extends EventDto {
    private String name;
    private Long childId;
    private InjuryType injuryType;
    private String description;
}