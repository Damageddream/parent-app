package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.entity.Illness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IllnessMapper {

    IllnessDto toDTO(Illness entity);

    Illness toEntity(IllnessDto dto);
}