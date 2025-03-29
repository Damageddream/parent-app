package bg.enterprise.parent_app.model;

import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.type.IllnessType;

public class CreateDtoFactory {

    public static IllnessDto createIllnessDto(Long id, Long childId, String name, IllnessType type, String description) {
        return IllnessDto.builder()
                .id(id)
                .childId(childId)
                .name(name)
                .illnessType(type)
                .description(description)
                .build();
    }
}
