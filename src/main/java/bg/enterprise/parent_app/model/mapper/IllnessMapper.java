package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.entity.Illness;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IllnessMapper {

    IllnessDto toDTO(Illness entity);

    Illness toEntity(IllnessDto dto);

    void updateIllnessFromDto(IllnessDto dto, @MappingTarget Illness entity);
}