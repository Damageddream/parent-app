package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.entity.Illness;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IllnessMapper {

    @Mapping(source = "child.id", target = "childId")
    IllnessDto toDTO(Illness entity);

    @Mapping(source = "childId", target = "child.id")
    Illness toEntity(IllnessDto dto);

    void updateIllnessFromDto(IllnessDto dto, @MappingTarget Illness entity);
}