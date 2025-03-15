package bg.enterprise.parent_app.model.mapper;

import bg.enterprise.parent_app.model.dto.ChronicConditionDto;
import bg.enterprise.parent_app.model.dto.DosageLogDto;
import bg.enterprise.parent_app.model.entity.ChronicCondition;
import bg.enterprise.parent_app.model.entity.DosageLog;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ChronicConditionMapper {

    ChronicConditionDto toDTO(ChronicCondition entity);

    ChronicCondition toEntity(ChronicConditionDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ChronicConditionDto chronicConditionDto, @MappingTarget ChronicCondition chronicCondition);
}