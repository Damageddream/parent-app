package bg.enterprise.parent_app.model.dto;

import bg.enterprise.parent_app.model.type.IllnessType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IllnessDto extends EventDto {
    private Long id;
    private String name;
    private Long childId;
    private IllnessType illnessType;
    private String description;
}