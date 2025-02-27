package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ChronicConditionDto;
import bg.enterprise.parent_app.model.entity.ChronicCondition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChronicConditionMapper {

    ChronicConditionDto toDTO(ChronicCondition entity);

    ChronicCondition toEntity(ChronicConditionDto dto);
}